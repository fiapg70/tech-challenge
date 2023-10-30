package br.com.postech.sevenfood.core.domain;

import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Tag(name = "Resident object")
public class Product implements Serializable {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String pic;
    private ProductCategory productCategory;
    private Restaurant restaurant;

    public void update(Long id, Product product) {
        this.id = id;
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.pic = product.getPic();
        this.productCategory = product.getProductCategory();
        this.restaurant = product.getRestaurant();
    }
}
