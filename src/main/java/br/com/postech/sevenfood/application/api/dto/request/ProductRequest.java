package br.com.postech.sevenfood.application.api.dto.request;

import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.domain.Restaurant;
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
@Tag(name = "Product object")
public class ProductRequest implements Serializable {

    @Schema(description = "Name of the Product.",
            example = "Coca-cola", required = true)
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Description of the Product.",
            example = "Coca-cola !L", required = true)
    @Size(min = 0, max = 255)
    private String description;

    @Schema(description = "Price of the Product.",
            example = "9.00", required = true)
    private BigDecimal price;

    @Schema(description = "Picture of the Product.",
            example = "/home/pic/bebida.png", required = true)
    private String pic;

    @Schema(description = "Product Category of the Product.",
            example = "Bebida", required = true, ref = "ProductCategory")
    private ProductCategory productCategory;

    @Schema(description = "Restaurant of the Product.",
            example = "seven food", required = true, ref = "Restaurant")
    private Restaurant restaurant;
}
