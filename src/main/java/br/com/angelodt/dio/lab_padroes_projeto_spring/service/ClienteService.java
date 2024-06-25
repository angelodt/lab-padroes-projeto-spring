package br.com.angelodt.dio.lab_padroes_projeto_spring.service;

import br.com.angelodt.dio.lab_padroes_projeto_spring.model.Cliente;


public interface ClienteService {

    //CRUD;
    void create(Cliente cliente);

    Cliente read(Long id);

    void update(Long id, Cliente cliente);

    void delete(Long id);

    Iterable<Cliente> getAll();

}
