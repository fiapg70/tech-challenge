package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.service.ProductService;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
@TestPropertySource("classpath:application-test.properties")
public class ProductResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductService service;

    private ProductCategory getProductCategory() {
        return ProductCategory.builder()
                .id(1L)
                .name("Bebida")
                .build();
    }

    private Restaurant getRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    private Product getProduct() {
        return Product.builder()
                .name("Bebida - coca cola")
                .pic("hhh")
                .price(BigDecimal.TEN)
                .description("Coca-Cola")
                .productCategory(getProductCategory())
                .restaurant(getRestaurant())
                .build();
    }

    private Product getProductUpdate() {
        return Product.builder()
                .id(1l)
                .name("Bebida - coca cola")
                .pic("hhh")
                .price(BigDecimal.TEN)
                .description("Coca-Cola")
                .productCategory(getProductCategory())
                .restaurant(getRestaurant())
                .build();
    }


    @Test
    void findsTaskById() throws Exception {
        Long id = 1l;

        mockMvc.perform(get("/v1/products/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bebida"));
    }

    @Test
    public void getAll() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").exists());
    }


    @Test
    public void create() throws Exception {
        String create = JsonUtil.getJson(getProduct());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/v1/products")
                        .content(create)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }


    @Test
    public void update() throws Exception {
        String update = JsonUtil.getJson(getProductUpdate());

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/v1/products/{id}", 1)
                        .content(update)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bebida"));
    }

    @Test
    public void delete() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/v1/products/{id}", 1) )
                .andExpect(status().isOk());
    }
}
