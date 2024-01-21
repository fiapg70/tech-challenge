package br.com.postech.sevenfood.infrastructure.entity.order;

import br.com.postech.sevenfood.core.utils.StatusPedidoEnum;
import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.entity.domain.AuditDomain;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class OrderEntity {

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
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientEntity client;

    @ManyToMany
    @JoinTable(name = "tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    @Column(name = "status", nullable = false)
    @NotNull(message = "o campo \"status\" é obrigario")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum statusPedidoEnum;

    public void update(Long id, OrderEntity order) {
        this.id = id;
        this.code = order.getCode();
        this.client = order.getClient();
        //this.products = order.getProducts();
    }
}
