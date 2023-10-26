package br.com.postech.sevenfood.application.database.mapper;

import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.infrastructure.productcategory.ProductCategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryEntity fromModelTpEntity(ProductCategory productCategory);
    @InheritInverseConfiguration
    ProductCategory fromEntityToModel(ProductCategoryEntity productCategoryEntity);

    List<ProductCategory> map(List<ProductCategoryEntity> productCategoryEntities);
}
