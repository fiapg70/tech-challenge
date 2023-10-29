package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
