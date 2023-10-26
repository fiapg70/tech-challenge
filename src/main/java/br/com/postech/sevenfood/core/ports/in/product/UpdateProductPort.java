package br.com.postech.sevenfood.core.ports.in.product;

import br.com.postech.sevenfood.core.domain.Product;

public interface UpdateProductPort {
    Product update(Long id, Product product);
}
