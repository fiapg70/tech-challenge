package br.com.postech.sevenfood.application.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class RestaurantRequest implements Serializable {

    @Schema(description = "Name of the Restaurant.",
            example = "Seven Food", required = true)
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Cnpj of the Restaurant.",
            example = "11.469.762/0001-58", required = true)
    @Size(min = 0, max = 255)
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

}
