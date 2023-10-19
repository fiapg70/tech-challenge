package br.com.postech.sevenfood.application.repository;

import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepositoryPort productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean remove(Long id) {
        return productRepository.remove(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.update(id, product);
    }
}
