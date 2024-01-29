package br.com.postech.sevenfood.core.ports.in.payment.impl;

import br.com.postech.sevenfood.core.domain.Payment;
import br.com.postech.sevenfood.core.ports.in.payment.PaymentStatusPort;
import br.com.postech.sevenfood.infrastructure.entity.payment.PaymentEntity;
import br.com.postech.sevenfood.infrastructure.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentStatusPortImpl implements PaymentStatusPort {

    private final PaymentRepository repository;

    @Override
    public Payment get(Long id) {
        PaymentEntity paymentEntity = repository.findByOrderId(id);

        if (paymentEntity == null) {
            return null;
        }

        return new Payment(paymentEntity.getId(), paymentEntity.getClient().getId(), paymentEntity.getOrderEntity()
                .getId(), paymentEntity.getStatus());
    }

}

