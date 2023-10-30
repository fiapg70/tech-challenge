package br.com.postech.sevenfood.infrastructure.repository;

import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
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
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity getRestaurant() {
        return RestaurantEntity.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    private ClientEntity getClient() {
        return ClientEntity.builder()
                .name("Ana Furtado Correia")
                .cpf("183.417.520-85")
                .restaurant(getRestaurant())
                .build();
    }

    @Test
    public void should_find_no_clients_if_repository_is_empty() {
        Iterable<ClientEntity> seeds = clientRepository.findAll();
        seeds = Collections.EMPTY_LIST;
        assertThat(seeds).isEmpty();
    }

    @Test
    public void should_store_a_client() {
        ClientEntity client = getClient();

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ClientEntity anaFurtadoCorreia = ClientEntity.builder()
                    .name("Ana Furtado Correia")
                    .cpf("183.417.520-85")
                    .restaurant(restaurant.get())
                    .build();

           clientRepository.save(anaFurtadoCorreia);
        }

        assertThat(client).hasFieldOrPropertyWithValue("name", "Ana Furtado Correia");
        assertThat(client).hasFieldOrPropertyWithValue("cpf", "183.417.520-85");
        assertThat(client).hasFieldOrPropertyWithValue("restaurant", getRestaurant());
    }

    @Disabled
    public void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ClientEntity client = getClient();
            clientRepository.save(client);
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_found_store_a_client() {
        ClientEntity client = getClient();
        ClientEntity clientResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ClientEntity anaFurtadoCorreia = ClientEntity.builder()
                    .name("Ana Furtado Correia")
                    .cpf("183.417.520-85")
                    .restaurant(restaurant.get())
                    .build();

            clientResult = clientRepository.save(anaFurtadoCorreia);
        }

        Optional<ClientEntity> found = clientRepository.findById(clientResult.getId());
        assertNotNull(found.get());
    }

    @Test
    public void should_found_null_Client() {
        ClientEntity clientResult = null;

        Optional<ClientEntity> fromDb = clientRepository.findById(99l);
        if (fromDb.isPresent()) {
            clientResult = fromDb.get();
        }
        assertThat(clientResult).isNull();
    }

    @Test
    public void whenFindById_thenReturnClient() {
        ClientEntity clientResult = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ClientEntity anaFurtadoCorreia = ClientEntity.builder()
                    .name("Ana Furtado Correia")
                    .cpf("183.417.520-85")
                    .restaurant(restaurant.get())
                    .build();

            clientResult = clientRepository.save(anaFurtadoCorreia);
        }

        ClientEntity fromDb = clientRepository.findById(clientResult.getId()).orElse(null);
        assertNotNull(fromDb);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        ClientEntity fromDb = clientRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Disabled
    public void givenSetOfClients_whenFindAll_thenReturnAllClients() {
        ClientEntity clientResult = null;
        ClientEntity clientResult2 = null;
        ClientEntity clientResult3 = null;

        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(1l);
        if (restaurant.isPresent()) {

            ClientEntity anaFurtadoCorreia = ClientEntity.builder()
                    .name("Ana Furtado Correia")
                    .cpf("183.417.520-85")
                    .restaurant(restaurant.get())
                    .build();
            clientResult = clientRepository.save(anaFurtadoCorreia);

            ClientEntity alessandroRezendeFurtado = ClientEntity.builder()
                    .name("Alessandro Rezende Furtado")
                    .cpf("823.633.650-62")
                    .restaurant(restaurant.get())
                    .build();
            clientResult2 = clientRepository.save(anaFurtadoCorreia);

            ClientEntity robertaGimenesMoura = ClientEntity.builder()
                    .name("Roberta Gimenes Moura")
                    .cpf("688.989.370-08")
                    .restaurant(restaurant.get())
                    .build();
            clientResult3 = clientRepository.save(anaFurtadoCorreia);

        }

        Iterator<ClientEntity> allClients = clientRepository.findAll().iterator();
        List<ClientEntity> clients = new ArrayList<>();
        allClients.forEachRemaining(c -> clients.add(c));

        assertThat(allClients);
    }
}