package br.com.postech.sevenfood.core.ports.in.payment;

import br.com.postech.sevenfood.core.domain.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentStatusPort {
    Payment get(Long id);
}
