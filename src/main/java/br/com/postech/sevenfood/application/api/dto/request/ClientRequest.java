package br.com.postech.sevenfood.application.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Client object")
public class ClientRequest implements Serializable {

    @Schema(description = "Name of the Client.",
            example = "Luara Balestero da Mata", required = true)
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Description of the Client.",
            example = "590.042.310-72", required = true)
    @Size(min = 0, max = 255)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Schema(description = "Restaurant of the Client.",
            example = "Seven Food", required = true, ref = "Restaurant")
    private RestaurantRequest restaurant;

}
