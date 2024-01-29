package br.com.postech.sevenfood.application.api.dto.response;

import br.com.postech.sevenfood.core.utils.PaymentEnum;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Status payment object")
public record PaymentStatusResponse(Long orderId, PaymentEnum status){

}
