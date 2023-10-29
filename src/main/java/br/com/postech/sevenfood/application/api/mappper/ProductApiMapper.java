package br.com.postech.sevenfood.application.api.mappper;

import br.com.postech.sevenfood.application.api.dto.request.ProductRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductApiMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "pic", target = "pic")
    @Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "restaurant", target = "restaurant")
    Product fromRquest(ProductRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ProductResponse fromEntidy(Product product);

   List<ProductResponse> map(List<Product> products);


}
