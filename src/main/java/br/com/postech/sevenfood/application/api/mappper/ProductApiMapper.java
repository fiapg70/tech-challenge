package br.com.postech.sevenfood.application.api.mappper;

import br.com.postech.sevenfood.application.api.dto.request.ProductRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductApiMapper {

    Product fromRquest(ProductRequest request);

    @InheritInverseConfiguration
    ProductResponse fromEntidy(Product product);

   List<ProductResponse> map(List<Product> products);


}
