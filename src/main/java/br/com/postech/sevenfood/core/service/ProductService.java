package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.ports.in.product.*;
import br.com.postech.sevenfood.core.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService implements CreateProductPort, UpdateProductPort, FindByIdProductPort, FindProductsPort, DeleteProductPort {

    private final ProductRepositoryPort productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product resultById = findById(id);
        if (resultById != null) {
            resultById.update(id, product);

            return productRepository.save(resultById);
        }

        return null;
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
    public boolean remove(Long id) {
        try {
            productRepository.remove(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
