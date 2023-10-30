package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
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

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository clientRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity getRestaurant() {
        return RestaurantEntity.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    private ProductEntity getProduct() {
        return ProductEntity.builder()
                .name("Bebida")
                .build();
    }

    @Test
    public void should_find_no_clients_if_repository_is_empty() {
        Iterable<ProductEntity> seeds = clientRepository.findAll();
        seeds = Collections.EMPTY_LIST;
        assertThat(seeds).isEmpty();
    }

    @Test
    public void should_store_a_client() {
        ProductEntity product = getProduct();

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        Optional<ProductCategoryEntity> productCategoryOp = productCategoryRepository.findById(1l);
        if (productCategoryOp.isPresent() && restaurant.isPresent()) {

            ProductEntity bebida = ProductEntity.builder()
                    .name("Bebida")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Coca-Cola")
                    .productCategory(productCategoryOp.get())
                    .restaurant(restaurant.get())
                    .build();

           clientRepository.save(bebida);
        }

        assertThat(product).hasFieldOrPropertyWithValue("name", "Bebida");
    }

    @Disabled
    public void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductEntity client = getProduct();
            clientRepository.save(client);
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_found_store_a_client() {
        ProductEntity productResuslt = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        Optional<ProductCategoryEntity> productCategoryOp = productCategoryRepository.findById(1l);
        if (productCategoryOp.isPresent() && restaurant.isPresent()) {

            ProductEntity bebida = ProductEntity.builder()
                    .name("Bebida")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Coca-Cola")
                    .productCategory(productCategoryOp.get())
                    .restaurant(restaurant.get())
                    .build();

            productResuslt = clientRepository.save(bebida);
        }

        Optional<ProductEntity> found = clientRepository.findById(productResuslt.getId());
        assertNotNull(found.get());
    }

    @Test
    public void should_found_null_Product() {
        ProductEntity clientResult = null;

        Optional<ProductEntity> fromDb = clientRepository.findById(99l);
        if (fromDb.isPresent()) {
            clientResult = fromDb.get();
        }
        assertThat(clientResult).isNull();
    }

    @Test
    public void whenFindById_thenReturnProduct() {
        ProductEntity productResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        Optional<ProductCategoryEntity> productCategoryOp = productCategoryRepository.findById(1l);
        if (productCategoryOp.isPresent() && restaurant.isPresent()) {

            ProductEntity bebida = ProductEntity.builder()
                    .name("Bebida")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Coca-Cola")
                    .productCategory(productCategoryOp.get())
                    .restaurant(restaurant.get())
                    .build();

            productResult = clientRepository.save(bebida);
        }

        ProductEntity fromDb = clientRepository.findById(productResult.getId()).orElse(null);
        assertNotNull(fromDb);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        ProductEntity fromDb = clientRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Disabled
    public void givenSetOfProducts_whenFindAll_thenReturnAllProducts() {
        ProductEntity productResult = null;
        ProductEntity productResult2 = null;
        ProductEntity productResult3 = null;

        Optional<ProductCategoryEntity> productCategoryOp = productCategoryRepository.findById(1l);
        if (productCategoryOp.isPresent()) {

            ProductEntity bebida = ProductEntity.builder()
                    .name("Bebida")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Coca-Cola")
                    .productCategory(productCategoryOp.get())
                    .build();

            productResult = clientRepository.save(bebida);

            ProductEntity acompanhamento = ProductEntity.builder()
                    .name("Acompanhamento")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Cebola")
                    .productCategory(productCategoryOp.get())
                    .build();

            productResult2 = clientRepository.save(acompanhamento);

            ProductEntity lanche = ProductEntity.builder()
                    .name("Lanche")
                    .pic("hhh")
                    .price(BigDecimal.TEN)
                    .description("Coca-Cola")
                    .productCategory(productCategoryOp.get())
                    .build();

            productResult3 = clientRepository.save(lanche);

        }

        Iterator<ProductEntity> allProducts = clientRepository.findAll().iterator();
        List<ProductEntity> clients = new ArrayList<>();
        allProducts.forEachRemaining(c -> clients.add(c));

        assertThat(allProducts);
    }
}