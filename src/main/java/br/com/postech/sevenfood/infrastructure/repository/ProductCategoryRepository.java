package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.productcategory.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
}
