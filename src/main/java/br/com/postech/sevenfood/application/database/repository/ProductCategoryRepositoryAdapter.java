package br.com.postech.sevenfood.application.database.repository;

import br.com.postech.sevenfood.application.database.mapper.ProductCategoryMapper;
import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.ports.out.ProductCategoryRepositoryPort;
import br.com.postech.sevenfood.infrastructure.productcategory.ProductCategoryEntity;
import br.com.postech.sevenfood.infrastructure.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductCategoryRepositoryAdapter implements ProductCategoryRepositoryPort {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategoryEntity productCategoryEntity = productCategoryMapper.fromModelTpEntity(productCategory);
        ProductCategoryEntity saved = productCategoryRepository.save(productCategoryEntity);
        return productCategoryMapper.fromEntityToModel(saved);
    }

    @Override
    public boolean remove(Long id) {
         try {
             productCategoryRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public ProductCategory findById(Long id) {
        Optional<ProductCategoryEntity> buProductCategory = productCategoryRepository.findById(id);
        if (buProductCategory.isPresent()) {
            return productCategoryMapper.fromEntityToModel(buProductCategory.get());
        }
        return null;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategoryEntity> all = productCategoryRepository.findAll();
        return productCategoryMapper.map(all);
    }

    @Override
    public ProductCategory update(Long id, ProductCategory productCategory) {
        Optional<ProductCategoryEntity> resultById = productCategoryRepository.findById(id);
        if (resultById.isPresent()) {

            ProductCategoryEntity productCategoryToChange = resultById.get();
            productCategoryToChange.update(id, productCategory);

            return productCategoryMapper.fromEntityToModel(productCategoryRepository.save(productCategoryToChange));
        }

        return null;
    }
}
