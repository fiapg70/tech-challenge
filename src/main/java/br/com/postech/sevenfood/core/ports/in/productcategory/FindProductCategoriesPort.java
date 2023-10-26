package br.com.postech.sevenfood.core.ports.in.productcategory;

import br.com.postech.sevenfood.core.domain.ProductCategory;

import java.util.List;

public interface FindProductCategoriesPort {
    List<ProductCategory> findAll();
}
