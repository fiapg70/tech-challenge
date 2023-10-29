package br.com.postech.sevenfood.core.ports.in.order;

import br.com.postech.sevenfood.core.domain.Order;

import java.util.List;

public interface FindOrdersPort {
    List<Order> findAll();
}
