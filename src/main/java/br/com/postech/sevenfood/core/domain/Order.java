package br.com.postech.sevenfood.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Tag(name = "Resident object")
public class Order implements Serializable {
    private Long id;
    private String code;
    private List<Product> products;

    public void update(Long id, Order order) {
        this.id = id;
        this.code = order.getCode();
        this.products = order.getProducts();
    }
}
