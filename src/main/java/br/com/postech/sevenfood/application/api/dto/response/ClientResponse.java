package br.com.postech.sevenfood.application.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Client object")
public class ClientResponse {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the Product.",
            example = "Vicente", required = true)
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Description of the Product.",
            example = "Vicente", required = true)
    @Size(min = 0, max = 255)
    private String cpf;

    private RestaurantResponse restaurant;

}
