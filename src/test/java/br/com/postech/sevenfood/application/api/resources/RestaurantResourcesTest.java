package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.service.RestaurantService;
import br.com.postech.sevenfood.util.JsonUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RestaurantResources.class)
public class RestaurantResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService service;

    private Restaurant getRestaurant() {
        return Restaurant.builder()
                .name("Seven Food")
                .cnpj("02.365.347/0001-63")
                .build();
    }

    @Disabled
    public void find_by_companyTypes_and_thenStatus200_and_companyType() throws Exception {
        Long id = 1l;
        String response = JsonUtil.getJson(getRestaurant());

        Restaurant restaurant = getRestaurant();
        given(service.findById(id)).willReturn(restaurant);

        mockMvc.perform(get("/api/v1/restaurants/{id}", id)).andDo(print()).andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(response));
    }

    @Disabled
    public void givenRestaurants_whenGetRestaurants_thenStatus201() throws Exception {

        Restaurant url = getRestaurant();
        when(this.service.save(url)).thenReturn(url);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/restaurants")
                .accept(MediaType.APPLICATION_JSON).content(JsonUtil.getJson(getRestaurant()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String content = response.getContentAsString();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}
