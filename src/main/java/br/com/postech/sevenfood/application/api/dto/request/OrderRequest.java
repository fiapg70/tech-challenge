package br.com.postech.sevenfood.application.api.dto.request;

import br.com.postech.sevenfood.core.utils.StatusPedidoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Order object")
public class OrderRequest implements Serializable {

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the Product.",
            example = "Vicente", required = true)
    @Size(min = 3, max = 255)
    private String code;

    @Schema(description = "Description of the Product.",
            example = "Vicente", required = true, ref = "Product")
    @Size(min = 0, max = 255)
    private List<ProductRequest> products;

    @Schema(description = "Client of the Product.",
            example = "Luara Balestero da Mata", required = true, ref = "Client")
    private ClientRequest client;

    private StatusPedidoEnum statusPedidoEnum;

}
