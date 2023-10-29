package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;

public interface UpdateOrderPort {
    Order update(Long id, Order order);
}
