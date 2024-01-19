package br.com.postech.sevenfood.application.database.repository;

import br.com.postech.sevenfood.application.database.mapper.ProductMapper;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.ports.out.ProductRepositoryPort;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import br.com.postech.sevenfood.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.fromModelTpEntity(product);
        ProductEntity saved = productRepository.save(productEntity);
        return productMapper.fromEntityToModel(saved);
    }

    @Override
    public boolean remove(Long id) {
         try {
            productRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Product findById(Long id) {
        Optional<ProductEntity> buProduct = productRepository.findById(id);
        if (buProduct.isPresent()) {
            return productMapper.fromEntityToModel(buProduct.get());
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> all = productRepository.findAll();
        return productMapper.map(all);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<ProductEntity> resultById = productRepository.findById(id);
        if (resultById.isPresent()) {

            ProductEntity productToChange = resultById.get();
            productToChange.update(id, product);

            return productMapper.fromEntityToModel(productRepository.save(productToChange));
        }

        return null;
    }
}
