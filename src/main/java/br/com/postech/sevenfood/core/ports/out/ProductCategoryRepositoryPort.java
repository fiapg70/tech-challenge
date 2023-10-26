package br.com.postech.sevenfood.core.ports.out;

import br.com.postech.sevenfood.core.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryRepositoryPort {
    ProductCategory save(ProductCategory productCategory);
    boolean remove(Long id);
    ProductCategory findById(Long id);
    List<ProductCategory> findAll();

    ProductCategory update(Long id, ProductCategory productCategory);
}
