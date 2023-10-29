package br.com.postech.sevenfood.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPreparation {
    private Long id;
    private String name;
    private List<Product> products;
}
