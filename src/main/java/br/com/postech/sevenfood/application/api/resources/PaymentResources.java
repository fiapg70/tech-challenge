package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.OrderRequest;
import br.com.postech.sevenfood.application.api.mappper.OrderApiMapper;
import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.ports.in.order.PaymentPort;
import br.com.postech.sevenfood.core.utils.PaymentEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/payments")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class PaymentResources {

    private final PaymentPort paymentPort;
    private final OrderApiMapper orderApiMapper;

    @Operation(summary = "Pay order", tags = {"payments", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = OrderResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaymentEnum> save(@Valid @RequestBody OrderRequest request) {
        try {
            log.info("Chegada" + request);
            Order order = orderApiMapper.fromRquest(request);
            PaymentEnum pay = paymentPort.pay(order);

                return ResponseEntity.ok(pay);

        } catch (Exception ex) {
            log.info("Erro: " + ex.getMessage());
        }
        return null;
    }
}
