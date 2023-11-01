package br.com.postech.sevenfood.infrastructure.entity.client;

import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.infrastructure.entity.domain.AuditDomain;
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
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class ClientEntity extends AuditDomain {

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
    @Column(name = "name", length = 255)
    private String name;

    @Schema(description = "cnpj of the Product.",
            example = "V$", required = true)
    @NotNull(message = "o campo \"cpf\" é obrigario")
    @Size(min = 3, max = 255)
    @Column(name = "cpf", length = 255)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Schema(description = "Resident of the User.",
            example = "1", required = true, ref = "User")
    @NotNull
    @OneToOne
    @JoinColumn(name = "restaurant_id", unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RestaurantEntity restaurant;

    public void update(Long id, Client client) {
        this.id = id;
        this.name = client.getName();
        this.cpf = client.getCpf();
    }
}
