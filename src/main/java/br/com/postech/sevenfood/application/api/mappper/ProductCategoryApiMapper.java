package br.com.postech.sevenfood.application.api.mappper;

import br.com.postech.sevenfood.application.api.dto.request.ProductCategoryRequest;
import br.com.postech.sevenfood.application.api.dto.request.ProductRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductCategoryResponse;
import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.domain.ProductCategory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryApiMapper {

    ProductCategory fromRquest(ProductCategoryRequest request);

    @InheritInverseConfiguration
    ProductCategoryResponse fromEntidy(ProductCategory productCategory);

   List<ProductCategoryResponse> map(List<ProductCategory> productCategories);


}
