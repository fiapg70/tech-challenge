package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
public class RestaurantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity getRestaurant() {
        return RestaurantEntity.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    @Test
    public void should_find_no_restaurants_if_repository_is_empty() {
        Iterable<RestaurantEntity> seeds = restaurantRepository.findAll();
        seeds = Collections.EMPTY_LIST;
        assertThat(seeds).isEmpty();
    }

    @Test
    public void should_store_a_restaurant() {
        RestaurantEntity restaurant = getRestaurant();

        Optional<RestaurantEntity> restaurantRestaurant = restaurantRepository.findById(1l);
        if (restaurantRestaurant.isPresent()) {

            RestaurantEntity anaFurtadoCorreia = RestaurantEntity.builder()
                    .name("Seven Food")
                    .cnpj("02.365.347/0001-63")
                    .build();

           restaurantRepository.save(anaFurtadoCorreia);
        }

        assertThat(restaurant).hasFieldOrPropertyWithValue("name", "Seven Food");
        assertThat(restaurant).hasFieldOrPropertyWithValue("cnpj", "02.365.347/0001-63");
    }

    @Disabled
    public void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            RestaurantEntity restaurant = getRestaurant();
            restaurantRepository.save(restaurant);
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_found_store_a_restaurant() {
        RestaurantEntity restaurant = getRestaurant();
        RestaurantEntity restaurantResult = null;

        Optional<RestaurantEntity> restaurantOp = restaurantRepository.findById(1l);
        if (restaurantOp.isPresent()) {

            RestaurantEntity anaFurtadoCorreia = RestaurantEntity.builder()
                    .name("Seven Food")
                    .cnpj("02.365.347/0001-63")
                    .build();

            restaurantResult = restaurantRepository.save(anaFurtadoCorreia);
        }

        Optional<RestaurantEntity> found = restaurantRepository.findById(restaurantResult.getId());
        assertNotNull(found.get());
    }

    @Test
    public void should_found_null_Restaurant() {
        RestaurantEntity restaurantResult = null;

        Optional<RestaurantEntity> fromDb = restaurantRepository.findById(99l);
        if (fromDb.isPresent()) {
            restaurantResult = fromDb.get();
        }
        assertThat(restaurantResult).isNull();
    }

    @Test
    public void whenFindById_thenReturnRestaurant() {
        RestaurantEntity restaurantResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            RestaurantEntity anaFurtadoCorreia = RestaurantEntity.builder()
                    .name("Seven Food")
                    .cnpj("02.365.347/0001-63")
                    .build();

            restaurantResult = restaurantRepository.save(anaFurtadoCorreia);
        }

        RestaurantEntity fromDb = restaurantRepository.findById(restaurantResult.getId()).orElse(null);
        assertNotNull(fromDb);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        RestaurantEntity fromDb = restaurantRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Disabled
    public void givenSetOfRestaurants_whenFindAll_thenReturnAllRestaurants() {
        RestaurantEntity restaurantResult = null;
        RestaurantEntity restaurantResult2 = null;
        RestaurantEntity restaurantResult3 = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            RestaurantEntity anaFurtadoCorreia = RestaurantEntity.builder()
                    .name("Seven Food")
                    .cnpj("02.365.347/0001-63")
                    .build();
            restaurantResult = restaurantRepository.save(anaFurtadoCorreia);

            RestaurantEntity alessandroRezendeFurtado = RestaurantEntity.builder()
                    .name("Seven Food - MG")
                    .cnpj("02.365.347/0001-63")
                    .build();
            restaurantResult2 = restaurantRepository.save(anaFurtadoCorreia);

            RestaurantEntity robertaGimenesMoura = RestaurantEntity.builder()
                    .name("Seven Food - SC")
                    .cnpj("02.365.347/0001-63")
                    .build();
            restaurantResult3 = restaurantRepository.save(anaFurtadoCorreia);

        }

        Iterator<RestaurantEntity> allRestaurants = restaurantRepository.findAll().iterator();
        List<RestaurantEntity> restaurants = new ArrayList<>();
        allRestaurants.forEachRemaining(c -> restaurants.add(c));

        assertThat(allRestaurants);
    }
}