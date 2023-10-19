package br.com.postech.sevenfood.application.mapper;

import br.com.postech.sevenfood.application.api.dto.request.ProductRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromRquest(ProductRequest request);

    @InheritInverseConfiguration
    ProductResponse fromEntidy(Product product);

   List<ProductResponse> map(List<Product> products);

    List<Product> mapi(List<ProductEntity> productEntities);
}
