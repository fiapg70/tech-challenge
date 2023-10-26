package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity fromModelTpEntity(Product product);
    @InheritInverseConfiguration
    Product fromEntityToModel(ProductEntity productEntity);

    List<Product> map(List<ProductEntity> productEntities);
}
