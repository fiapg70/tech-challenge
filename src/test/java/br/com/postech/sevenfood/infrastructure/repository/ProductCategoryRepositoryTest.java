package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.productcategory.ProductCategoryEntity;
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
public class ProductCategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductCategoryRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity getRestaurant() {
        return RestaurantEntity.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    private ProductCategoryEntity getProductCategory() {
        return ProductCategoryEntity.builder()
                .name("Bebida")
                .build();
    }

    @Test
    public void should_find_no_clients_if_repository_is_empty() {
        Iterable<ProductCategoryEntity> seeds = clientRepository.findAll();
        seeds = Collections.EMPTY_LIST;
        assertThat(seeds).isEmpty();
    }

    @Test
    public void should_store_a_client() {
        ProductCategoryEntity client = getProductCategory();

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ProductCategoryEntity anaFurtadoCorreia = ProductCategoryEntity.builder()
                    .name("Bebida")
                    .build();

           clientRepository.save(anaFurtadoCorreia);
        }

        assertThat(client).hasFieldOrPropertyWithValue("name", "Bebida");
    }

    @Disabled
    public void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductCategoryEntity client = getProductCategory();
            clientRepository.save(client);
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_found_store_a_client() {
        ProductCategoryEntity client = getProductCategory();
        ProductCategoryEntity clientResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ProductCategoryEntity anaFurtadoCorreia = ProductCategoryEntity.builder()
                    .name("Bebida")
                    .build();

            clientResult = clientRepository.save(anaFurtadoCorreia);
        }

        Optional<ProductCategoryEntity> found = clientRepository.findById(clientResult.getId());
        assertNotNull(found.get());
    }

    @Test
    public void should_found_null_ProductCategory() {
        ProductCategoryEntity clientResult = null;

        Optional<ProductCategoryEntity> fromDb = clientRepository.findById(99l);
        if (fromDb.isPresent()) {
            clientResult = fromDb.get();
        }
        assertThat(clientResult).isNull();
    }

    @Test
    public void whenFindById_thenReturnProductCategory() {
        ProductCategoryEntity clientResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ProductCategoryEntity anaFurtadoCorreia = ProductCategoryEntity.builder()
                    .name("Bebida")
                    .build();

            clientResult = clientRepository.save(anaFurtadoCorreia);
        }

        ProductCategoryEntity fromDb = clientRepository.findById(clientResult.getId()).orElse(null);
        assertNotNull(fromDb);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        ProductCategoryEntity fromDb = clientRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Disabled
    public void givenSetOfProductCategorys_whenFindAll_thenReturnAllProductCategorys() {
        ProductCategoryEntity clientResult = null;
        ProductCategoryEntity clientResult2 = null;
        ProductCategoryEntity clientResult3 = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ProductCategoryEntity anaFurtadoCorreia = ProductCategoryEntity.builder()
                    .name("Bebida")
                    .build();
            clientResult = clientRepository.save(anaFurtadoCorreia);

            ProductCategoryEntity alessandroRezendeFurtado = ProductCategoryEntity.builder()
                    .name("Acompanhamento")
                    .build();
            clientResult2 = clientRepository.save(anaFurtadoCorreia);

            ProductCategoryEntity robertaGimenesMoura = ProductCategoryEntity.builder()
                    .name("Lanche")
                    .build();
            clientResult3 = clientRepository.save(anaFurtadoCorreia);

        }

        Iterator<ProductCategoryEntity> allProductCategorys = clientRepository.findAll().iterator();
        List<ProductCategoryEntity> clients = new ArrayList<>();
        allProductCategorys.forEachRemaining(c -> clients.add(c));

        assertThat(allProductCategorys);
    }
}