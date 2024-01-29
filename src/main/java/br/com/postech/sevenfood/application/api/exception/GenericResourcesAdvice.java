package br.com.postech.sevenfood.application.api.exception;

import br.com.postech.sevenfood.application.api.dto.response.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericResourcesAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericErrorResponse> handleException(Exception ex) {
        String errorMessage = "Erro ao processar a requisição. Detalhes: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericErrorResponse(errorMessage));
    }
}
