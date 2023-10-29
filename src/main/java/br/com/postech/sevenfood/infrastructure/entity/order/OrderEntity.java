package br.com.postech.sevenfood.infrastructure.entity.order;

import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.entity.domain.AuditDomain;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import br.com.postech.sevenfood.infrastructure.entity.restaurant.RestaurantEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class OrderEntity extends AuditDomain {

    @Schema(description = "Unique identifier of the Product.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "name of the Product.",
            example = "V$", required = true)
    @NotNull(message = "o campo \"name\" é obrigario")
    @Size(min = 3, max = 255)
    @Column(name = "code", length = 255)
    private String code;

    @Schema(description = "Client of the User.",
            example = "1", required = true, ref = "User")
    @NotNull
    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientEntity client;

    @ManyToMany
    @JoinTable(name = "tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    public void update(Long id, Order order) {
        this.id = id;
        this.code = order.getCode();
        this.client = order.getClient();
        //this.products = order.getProducts();
    }
}
