package br.com.postech.sevenfood.application.api.dto.request;

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
public class ProductCategoryRequest implements Serializable {

    @Schema(description = "Name of the Product Category.",
            example = "Bebida", required = true)
    @Size(min = 3, max = 255)
    private String name;
}
