package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.ports.in.restaurant.*;
import br.com.postech.sevenfood.core.ports.out.RestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantService implements CreateRestaurantPort, UpdateRestaurantPort, FindByIdRestaurantPort, FindRestaurantsPort, DeleteRestaurantPort {

    private final RestaurantRepositoryPort restaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant resultById = findById(id);
        if (resultById != null) {
            resultById.update(id, restaurant);

            return restaurantRepository.save(resultById);
        }

        return null;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> findAll() {
       return restaurantRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        try {
            restaurantRepository.remove(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
