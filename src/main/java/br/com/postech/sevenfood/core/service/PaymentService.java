package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.application.api.dto.response.PaymentStatusResponse;
import br.com.postech.sevenfood.application.database.mapper.OrderMapper;
import br.com.postech.sevenfood.application.event.PaymentApprovedEvent;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.domain.Payment;
import br.com.postech.sevenfood.core.ports.in.order.OrderPreparationPort;
import br.com.postech.sevenfood.core.ports.in.payment.PaymentPort;
import br.com.postech.sevenfood.core.ports.in.payment.PaymentStatusPort;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import br.com.postech.sevenfood.infrastructure.entity.order.OrderEntity;
import br.com.postech.sevenfood.infrastructure.entity.payment.PaymentEntity;
import br.com.postech.sevenfood.infrastructure.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService implements PaymentPort {

    private final OrderPreparationPort port;

    private final PaymentStatusPort paymentStatusPort;

    private final PaymentRepository repository;

    private final OrderMapper mapper;

    private final ApplicationEventPublisher eventPublisher;


    @Override
    public PaymentEnum pay(Order order) throws JsonProcessingException {

        Order order1 = payMock(order);
        eventPublisher.publishEvent(new PaymentApprovedEvent(order1));

        if (order1.getPaymentEnum() == PaymentEnum.APROVADO) {
            OrderEntity orderEntity = mapper.fromModelTpEntity(order1);
            repository.save(new PaymentEntity(null, orderEntity.getClient(), orderEntity, orderEntity.getStatusPedidoEnum().getCod()));

            port.queueOrder(order);

            return PaymentEnum.APROVADO;
        }

        return PaymentEnum.RECUSADO;
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
        if ("733.966.987-62".equals(order.getClient().getCpf())) {
            order.setPaymentEnum(PaymentEnum.APROVADO);
        } else {
            order.setPaymentEnum(PaymentEnum.RECUSADO);
        }
        return order;
    }
}
