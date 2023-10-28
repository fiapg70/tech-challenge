package br.com.postech.sevenfood.core.ports.out;

import br.com.postech.sevenfood.core.domain.Client;

import java.util.List;

public interface ClientRepositoryPort {
    Client save(Client client);
    boolean remove(Long id);
    Client findById(Long id);
    List<Client> findAll();
    Client update(Long id, Client client);
}
