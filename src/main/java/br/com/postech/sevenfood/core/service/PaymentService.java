package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.ports.in.order.OrderPreparationPort;
import br.com.postech.sevenfood.core.ports.in.order.PaymentPort;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService implements PaymentPort {

    private final OrderPreparationPort port;


    @Override
    public PaymentEnum pay(Order order) throws JsonProcessingException {

        Order order1 = payMock(order);

        if (order1.getPaymentEnum() == PaymentEnum.APROVADO) {

            port.queueOrder(order);

            return PaymentEnum.APROVADO;
        }

        return null;
    }

    private Order payMock(Order order) {
        order.setPaymentEnum(PaymentEnum.APROVADO);
      return order;
    }

}
