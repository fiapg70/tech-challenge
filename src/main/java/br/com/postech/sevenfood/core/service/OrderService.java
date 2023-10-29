package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.ports.in.order.*;
import br.com.postech.sevenfood.core.ports.out.OrderRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService implements CreateOrderPort, UpdateOrderPort, FindByIdOrderPort, FindOrdersPort, DeleteOrderPort {

    private final OrderRepositoryPort orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        Order resultById = findById(id);
        if (resultById != null) {
            resultById.update(id, order);

            return orderRepository.save(resultById);
        }

        return null;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
       return orderRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        try {
            orderRepository.remove(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
