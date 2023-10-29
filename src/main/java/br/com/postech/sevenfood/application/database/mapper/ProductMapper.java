package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "pic", target = "pic")
    @Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "restaurant", target = "restaurant")
    ProductEntity fromModelTpEntity(Product product);
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    Product fromEntityToModel(ProductEntity productEntity);

    List<Product> map(List<ProductEntity> productEntities);
}
