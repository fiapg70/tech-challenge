package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;

public interface CreateOrderPort {
    Order save(Order order);
}
