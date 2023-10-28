package br.com.postech.sevenfood.application.api.mappper;

import br.com.postech.sevenfood.application.api.dto.request.ClientRequest;
import br.com.postech.sevenfood.application.api.dto.response.ClientResponse;
import br.com.postech.sevenfood.core.domain.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientApiMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "restaurant", target = "restaurant")
    Client fromRquest(ClientRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ClientResponse fromEntidy(Client client);

    List<ClientResponse> map(List<Client> clients);

}
