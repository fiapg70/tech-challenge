package br.com.postech.sevenfood.core.ports.in.restaurant;

import br.com.postech.sevenfood.core.domain.Restaurant;

import java.util.List;

public interface FindRestaurantsPort {
    List<Restaurant> findAll();
}
