package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "restaurant", target = "restaurant")
    ClientEntity fromModelTpEntity(Client client);
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    Client fromEntityToModel(ClientEntity clientEntity);

    List<Client> map(List<ClientEntity> clientEntities);
}
