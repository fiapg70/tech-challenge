package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.infrastructure.entity.order.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "code", target = "code")
    @Mapping(source = "products", target = "products")
    OrderEntity fromModelTpEntity(Order order);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    Order fromEntityToModel(OrderEntity orderEntity);

    List<Order> map(List<OrderEntity> orderEntities);
}
