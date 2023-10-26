package br.com.postech.sevenfood.core.ports.in.productcategory;

import br.com.postech.sevenfood.core.domain.ProductCategory;

public interface FindByIdProductCategoryPort {
    ProductCategory findById(Long id);
}
