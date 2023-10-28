package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.core.ports.in.client.*;
import br.com.postech.sevenfood.core.ports.out.ClientRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService implements CreateClientPort, UpdateClientPort, FindByIdClientPort, FindClientsPort, DeleteClientPort {

    private final ClientRepositoryPort clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client resultById = findById(id);
        if (resultById != null) {
            resultById.update(id, client);

            return clientRepository.save(resultById);
        }

        return null;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAll() {
       return clientRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        try {
            clientRepository.remove(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
