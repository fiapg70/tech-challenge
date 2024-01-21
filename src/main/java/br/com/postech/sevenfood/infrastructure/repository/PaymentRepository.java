package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p FROM PaymentEntity p WHERE p.orderEntity.id = :orderId")
    PaymentEntity findByOrderId(@Param("orderId") Long orderId);
}
