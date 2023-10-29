package br.com.postech.sevenfood.application.api.mappper;

import br.com.postech.sevenfood.application.api.dto.request.OrderRequest;
import br.com.postech.sevenfood.application.api.dto.response.OrderResponse;
import br.com.postech.sevenfood.core.domain.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderApiMapper {

    @Mapping(source = "code", target = "code")
    @Mapping(source = "products", target = "products")
    Order fromRquest(OrderRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    OrderResponse fromEntidy(Order order);

   List<OrderResponse> map(List<Order> orders);


}
