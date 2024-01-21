package br.com.postech.sevenfood.infrastructure.entity.payment;

import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.entity.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_payment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Payment object")
public class PaymentEntity {

    @Schema(description = "Unique identifier of the Product.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Client of the User.",
            example = "1", required = true, ref = "User")
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientEntity client;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private OrderEntity orderEntity;

    private Long status;
}

