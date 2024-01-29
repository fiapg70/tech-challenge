package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.utils.StatusPedidoEnum;

public interface UpdateOrderPort {
    Order update(Long id, Order order);
}
