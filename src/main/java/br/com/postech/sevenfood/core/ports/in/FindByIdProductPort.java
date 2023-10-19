package br.com.postech.sevenfood.core.ports.in;

import br.com.postech.sevenfood.core.domain.Product;

public interface FindByIdProductPort {
    Product findById(Long id);
}
