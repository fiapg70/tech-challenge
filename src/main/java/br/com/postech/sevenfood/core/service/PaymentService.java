package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.application.api.dto.response.PaymentStatusResponse;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.domain.Payment;
import br.com.postech.sevenfood.core.ports.in.order.OrderPreparationPort;
import br.com.postech.sevenfood.core.ports.in.payment.PaymentPort;
import br.com.postech.sevenfood.core.ports.in.payment.PaymentStatusPort;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService implements PaymentPort {

    private final OrderPreparationPort port;

    private final PaymentStatusPort paymentStatusPort;


    @Override
    public PaymentEnum pay(Order order) throws JsonProcessingException {

        Order order1 = payMock(order);

        if (order1.getPaymentEnum() == PaymentEnum.APROVADO) {

            port.queueOrder(order);

            return PaymentEnum.APROVADO;
        }

        return null;
    }

    @Override
    public PaymentStatusResponse get(Long id) {
        Payment payment = paymentStatusPort.get(id);
        if (payment == null) {
            return null;
        }

        return new PaymentStatusResponse(payment.orderId(), PaymentEnum.getByCod(payment.status()));
    }

    private Order payMock(Order order) {
        if("733.966.987-62".equals(order.getClient().getCpf())) {
            order.setPaymentEnum(PaymentEnum.APROVADO);
        } else {
            order.setPaymentEnum(PaymentEnum.RECUSADO);
        }
      return order;
    }
}
