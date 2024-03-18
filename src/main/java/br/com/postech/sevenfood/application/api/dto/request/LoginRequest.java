package br.com.postech.sevenfood.application.api.dto.request;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Product object")
public class LoginRequest {
    private String username;
    private String password;
}
