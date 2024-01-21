package br.com.postech.sevenfood.application.api.dto.response;

import br.com.postech.sevenfood.core.utils.StatusPedidoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Product object")
public class OrderResponse implements Comparator<OrderResponse> {

    private ClientResponse client;

    @Schema(description = "Unique identifier of the Driver.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Name of the Product.",
            example = "Vicente", required = true)
    @Size(min = 3, max = 255)
    private String code;

    @Schema(description = "Description of the Product.",
            example = "Vicente", required = true)
    @Size(min = 0, max = 255)
    private List<ProductResponse> products;

    private StatusPedidoEnum statusPedidoEnum;

    @Override
    public int compare(OrderResponse o1, OrderResponse o2) {
        // Define a ordem desejada para o status do pedido
        List<StatusPedidoEnum> order = List.of(
                StatusPedidoEnum.PRONTO,
                StatusPedidoEnum.EM_PREPARACAO,
                StatusPedidoEnum.RECEBIDO
        );

            // Compara o statusPedidoEnum usando a ordem definida
            int statusComparison = Integer.compare(
                    order.indexOf(o1.getStatusPedidoEnum()),
                    order.indexOf(o2.getStatusPedidoEnum())
            );


            // Se os status são diferentes, retorna a comparação
            if (statusComparison != 0) {
                return statusComparison;
            }
        // Se os status são iguais, compare pelos IDs em ordem crescente
        return Long.compare(o1.getId(), o2.getId());
    }
}
