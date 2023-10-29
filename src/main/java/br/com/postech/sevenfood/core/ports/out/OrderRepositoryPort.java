package br.com.postech.sevenfood.core.ports.out;

import br.com.postech.sevenfood.core.domain.Order;

import java.util.List;

public interface OrderRepositoryPort {
    Order save(Order order);
    boolean remove(Long id);
    Order findById(Long id);
    List<Order> findAll();
    Order update(Long id, Order order);
}
