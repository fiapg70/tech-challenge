package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.ports.out.RestaurantRepositoryPort;
import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import br.com.postech.sevenfood.infrastructure.repository.RestaurantRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepositoryPort restaurantRepository;

    private Restaurant getRestaurant() {
        return Restaurant.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    public void getAllEmployeesTest() {
        List<Restaurant> list = new ArrayList<>();

        Restaurant client = getRestaurant();
        Restaurant client1 = getRestaurant();
        Restaurant client2 = getRestaurant();

        list.add(client);
        list.add(client1);
        list.add(client2);

        when(restaurantRepository.findAll()).thenReturn(list);

        // test
        List<Restaurant> restaurants = restaurantService.findAll();

        assertEquals(0, restaurants.size());
        //verify(restaurantRepository, times(1)).findAll();
    }

    @Disabled
    public void getRestaurantByIdTest() {
        Restaurant restaurant1 = getRestaurant();
        when(restaurantRepository.findById(1L)).thenReturn(restaurant1);

        Restaurant restaurant = restaurantService.findById(1L);

        assertEquals("Seven Food", restaurant.getName());
        assertEquals("02.365.347/0001-63", restaurant.getCnpj());
    }

    /*@Test
    public void getFindRestaurantByShortIdTest() {
        Restaurant client = getRestaurant();
        when(restaurantService.findById(1l)).thenReturn(Optional.ofNullable(client));

        Optional<Restaurant> result = restaurantService.findById(1l);

        assertEquals("Everis", client.getName());
        assertEquals("root@localhost", client.getEmail());
    }

    @Test
    public void createRestaurantTest() {
        Restaurant url = getRestaurant();
        restaurantService.save(url);

        verify(restaurantRepository, times(1)).save(url);
    }

    @Test
    public void createAndStoreRestaurantTest() {
        Restaurant client = getRestaurant();
        restaurantService.save(client);

        when(restaurantService.save(client)).thenReturn(client);
        Restaurant result = restaurantService.save(client);

        assertEquals("root@localhost", result.getEmail());
    }

    @Test(expected = URLException.class)
    public void createAndStoreRestaurantTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("Restaurant is not valid!");

        Restaurant client = getRestaurant();
        restaurantService.save(client);

        when(restaurantService.save(client)).thenReturn(client);
        Restaurant result = restaurantService.save(client);

        assertEquals("root@localhost", result.getEmail());
    }*/

}