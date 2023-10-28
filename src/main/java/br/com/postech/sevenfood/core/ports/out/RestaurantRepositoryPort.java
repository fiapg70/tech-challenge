package br.com.postech.sevenfood.core.ports.out;

import br.com.postech.sevenfood.core.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryPort {
    Restaurant save(Restaurant restaurant);
    boolean remove(Long id);
    Restaurant findById(Long id);
    List<Restaurant> findAll();
    Restaurant update(Long id, Restaurant restaurant);
}
