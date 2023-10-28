package br.com.postech.sevenfood.core.domain;

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
public class Restaurant implements Serializable {
    private Long id;
    private String name;
    private String cnpj;

    public void update(Long id, Restaurant product) {
        this.id = id;
        this.name = product.getName();
        this.cnpj = product.getCnpj();
    }
}
