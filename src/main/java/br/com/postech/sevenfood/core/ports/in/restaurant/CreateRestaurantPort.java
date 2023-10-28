package br.com.postech.sevenfood.core.ports.in.restaurant;

import br.com.postech.sevenfood.core.domain.Restaurant;

public interface CreateRestaurantPort {
    Restaurant save(Restaurant restaurant);
}
