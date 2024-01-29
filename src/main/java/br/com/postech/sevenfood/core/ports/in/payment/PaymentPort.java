package br.com.postech.sevenfood.core.ports.in.payment;

import br.com.postech.sevenfood.application.api.dto.response.PaymentStatusResponse;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentPort {

    PaymentEnum pay(Order order) throws JsonProcessingException;

    PaymentStatusResponse get(Long id);
}
