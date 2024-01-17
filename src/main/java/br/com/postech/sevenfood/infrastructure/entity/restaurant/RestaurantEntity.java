package br.com.postech.sevenfood.infrastructure.entity.restaurant;

import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.infrastructure.entity.domain.AuditDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "tb_restaurant")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class RestaurantEntity extends AuditDomain {

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
    @NotNull(message = "o campo \"cnpj\" é obrigatorio")
    @Size(min = 3, max = 255)
    @Column(name = "cnpj", length = 255)
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    public void update(Long id, Restaurant restaurant) {
        this.id = id;
        this.name = restaurant.getName();
        this.cnpj = restaurant.getCnpj();
    }
}
