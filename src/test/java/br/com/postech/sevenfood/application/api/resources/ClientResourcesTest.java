package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.service.ClientService;
import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import br.com.postech.sevenfood.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
@TestPropertySource("classpath:application-test.properties")
public class ClientResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ClientService service;

    private Restaurant getRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    private Client getClient() {
        return Client.builder()
                .name("Ana Furtado Correia")
                .cpf("183.417.520-85")
                .restaurant(getRestaurant())
                .build();
    }

    private Client getClientUpdate() {
        return Client.builder()
                .name("Ana Furtado Correia")
                .cpf("183.417.520-85")
                .restaurant(getRestaurant())
                .build();
    }


    @Test
    void findsTaskById() throws Exception {
        Long id = 1l;

        mockMvc.perform(get("/v1/clients/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana Furtado Correia"));
    }

    @Test
    public void getAll() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/clients")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").exists());
    }


    @Test
    public void create() throws Exception {
        String create = JsonUtil.getJson(getClient());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/v1/clients")
                        .content(create)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }


    @Test
    public void update() throws Exception {
        String update = JsonUtil.getJson(getClientUpdate());

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/v1/clients/{id}", 1)
                        .content(update)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ana Furtado Correia"));
    }

    @Test
    public void delete() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/v1/clients/{id}", 1) )
                .andExpect(status().isOk());
    }
}
