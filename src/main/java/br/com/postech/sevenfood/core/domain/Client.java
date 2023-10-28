package br.com.postech.sevenfood.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Tag(name = "Resident object")
public class Client implements Serializable {
    private Long id;
    private String name;
    private String cpf;
    private Restaurant restaurant;

    public void update(Long id, Client client) {
        this.id = id;
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.restaurant = client.getRestaurant();
    }
}
