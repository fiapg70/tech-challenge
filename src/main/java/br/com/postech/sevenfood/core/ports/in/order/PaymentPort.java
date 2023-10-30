package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentPort {

    PaymentEnum pay(Order order) throws JsonProcessingException;
}
