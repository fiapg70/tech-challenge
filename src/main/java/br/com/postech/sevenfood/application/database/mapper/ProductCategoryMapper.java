package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.infrastructure.entity.productcategory.ProductCategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    @Mapping(source = "name", target = "name")
    ProductCategoryEntity fromModelTpEntity(ProductCategory productCategory);
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ProductCategory fromEntityToModel(ProductCategoryEntity productCategoryEntity);

    List<ProductCategory> map(List<ProductCategoryEntity> productCategoryEntities);
}
