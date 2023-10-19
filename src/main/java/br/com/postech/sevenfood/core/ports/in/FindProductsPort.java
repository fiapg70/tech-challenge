package br.com.postech.sevenfood.core.ports.in;

import br.com.postech.sevenfood.core.domain.Product;

import java.util.List;

public interface FindProductsPort {
    List<Product> findAll();
}
