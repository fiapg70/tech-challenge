package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderPreparationPort {

    void queueOrder(Order order) throws JsonProcessingException;

}
