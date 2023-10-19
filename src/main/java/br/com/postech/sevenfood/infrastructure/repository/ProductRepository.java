package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
