package br.com.angelodt.dio.lab_padroes_projeto_spring.controller;

import br.com.angelodt.dio.lab_padroes_projeto_spring.handlerException.CampoObrigatorioException;
import br.com.angelodt.dio.lab_padroes_projeto_spring.model.Cliente;
import br.com.angelodt.dio.lab_padroes_projeto_spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        if(cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new CampoObrigatorioException("Cliente não inserido. O %s é obrigatório!","CEP");
        } else {
            clienteService.create(cliente);
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> read(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.update(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.getAll());
    }
}
