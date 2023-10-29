package br.com.postech.sevenfood.application.database.repository;

import br.com.postech.sevenfood.application.database.mapper.OrderMapper;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.ports.out.OrderRepositoryPort;
import br.com.postech.sevenfood.infrastructure.entity.order.OrderEntity;
import br.com.postech.sevenfood.infrastructure.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.fromModelTpEntity(order);
        OrderEntity saved = orderRepository.save(orderEntity);
        return orderMapper.fromEntityToModel(saved);
    }

    @Override
    public boolean remove(Long id) {
         try {
            orderRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Order findById(Long id) {
        Optional<OrderEntity> buOrder = orderRepository.findById(id);
        if (buOrder.isPresent()) {
            return orderMapper.fromEntityToModel(buOrder.get());
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> all = orderRepository.findAll();
        return orderMapper.map(all);
    }

    @Override
    public Order update(Long id, Order order) {
        Optional<OrderEntity> resultById = orderRepository.findById(id);
        if (resultById.isPresent()) {

            OrderEntity orderToChange = resultById.get();
            orderToChange.update(id, order);

            return orderMapper.fromEntityToModel(orderRepository.save(orderToChange));
        }

        return null;
    }
}
