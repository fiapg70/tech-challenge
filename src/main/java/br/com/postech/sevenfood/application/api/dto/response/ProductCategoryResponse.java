package br.com.postech.sevenfood.application.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
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
@Tag(name = "Product Category object")
public class ProductCategoryResponse implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the Product.",
            example = "Vicente", required = true)
    @Size(min = 3, max = 255)
    private String name;
}
