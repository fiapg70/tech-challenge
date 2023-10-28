package br.com.postech.sevenfood.application.database.repository;

import br.com.postech.sevenfood.application.database.mapper.ClientMapper;
import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.core.ports.out.ClientRepositoryPort;
import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientMapper.fromModelTpEntity(client);
        ClientEntity saved = clientRepository.save(clientEntity);
        return clientMapper.fromEntityToModel(saved);
    }

    @Override
    public boolean remove(Long id) {
         try {
            clientRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Client findById(Long id) {
        Optional<ClientEntity> buClient = clientRepository.findById(id);
        if (buClient.isPresent()) {
            return clientMapper.fromEntityToModel(buClient.get());
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> all = clientRepository.findAll();
        return clientMapper.map(all);
    }

    @Override
    public Client update(Long id, Client client) {
        Optional<ClientEntity> resultById = clientRepository.findById(id);
        if (resultById.isPresent()) {

            ClientEntity clientToChange = resultById.get();
            clientToChange.update(id, client);

            return clientMapper.fromEntityToModel(clientRepository.save(clientToChange));
        }

        return null;
    }
}
