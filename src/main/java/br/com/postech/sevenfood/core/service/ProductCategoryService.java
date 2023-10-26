package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.ports.in.productcategory.*;
import br.com.postech.sevenfood.core.ports.out.ProductCategoryRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductCategoryService implements CreateProductCategoryPort, UpdateProductCategoryPort, FindByIdProductCategoryPort, FindProductCategoriesPort, DeleteProductCategoryPort {

    private final ProductCategoryRepositoryPort productCategoryRepository;

    @Override
    public ProductCategory save(ProductCategory product) {
        return productCategoryRepository.save(product);
    }

    @Override
    public ProductCategory update(Long id, ProductCategory product) {
        ProductCategory resultById = findById(id);
        if (resultById != null) {
            resultById.update(id, product);

            return productCategoryRepository.save(resultById);
        }

        return null;
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
       return productCategoryRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        try {
            productCategoryRepository.remove(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
