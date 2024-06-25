package br.com.angelodt.dio.lab_padroes_projeto_spring.service.impl;

import br.com.angelodt.dio.lab_padroes_projeto_spring.model.Cliente;
import br.com.angelodt.dio.lab_padroes_projeto_spring.model.ClienteRepository;
import br.com.angelodt.dio.lab_padroes_projeto_spring.model.Endereco;
import br.com.angelodt.dio.lab_padroes_projeto_spring.model.EnderecoRepository;
import br.com.angelodt.dio.lab_padroes_projeto_spring.service.ClienteService;
import br.com.angelodt.dio.lab_padroes_projeto_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public void create(Cliente cliente) {
        inserirCliente(cliente);
    }

    @Override
    public Cliente read(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void update(Long id, Cliente cliente) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);
        if(clienteDB.isPresent()) {
            cliente.setId(clienteDB.get().getId());
            /*if(cliente.getEndereco() != null && !cliente.getEndereco().getCep().equals(clienteDB.get().getEndereco().getCep())) {

            }*/
            inserirCliente(cliente);
        }
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Iterable<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    private void inserirCliente(Cliente cliente) {
        if(cliente.getEndereco() != null && cliente.getEndereco().getCep() != null) {
            Endereco endereco = getEndereco(cliente.getEndereco().getCep());
            if(endereco != null) {
                cliente.setEndereco(endereco);
                clienteRepository.save(cliente);
            }
        }
    }

    private Endereco getEndereco(String cep) {
        Endereco endereco = null;
        if(null != cep && !cep.isEmpty() && !cep.isBlank()) {
            endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
        }
        return endereco;
    }
}
