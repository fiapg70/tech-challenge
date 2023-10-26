package br.com.postech.sevenfood.core.ports.in.product;

import br.com.postech.sevenfood.core.domain.Product;

import java.util.List;

public interface FindProductsPort {
    List<Product> findAll();
}
