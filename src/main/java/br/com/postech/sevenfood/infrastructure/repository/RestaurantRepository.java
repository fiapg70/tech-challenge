package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
