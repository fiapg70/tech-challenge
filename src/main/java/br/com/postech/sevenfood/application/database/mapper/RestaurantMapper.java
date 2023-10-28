package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "cnpj", target = "cnpj")
    RestaurantEntity fromModelTpEntity(Restaurant restaurant);
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    Restaurant fromEntityToModel(RestaurantEntity restaurantEntity);

    List<Restaurant> map(List<RestaurantEntity> restaurantEntities);
}
