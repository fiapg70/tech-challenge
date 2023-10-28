package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.ClientRequest;
import br.com.postech.sevenfood.application.api.dto.response.ClientResponse;
import br.com.postech.sevenfood.application.api.mappper.ClientApiMapper;
import br.com.postech.sevenfood.commons.util.RestUtils;
import br.com.postech.sevenfood.core.domain.Client;
import br.com.postech.sevenfood.core.ports.in.client.*;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/clients")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ClientResources {

    private final CreateClientPort createClientPort;
    private final DeleteClientPort deleteClientPort;
    private final FindByIdClientPort findByIdClientPort;
    private final FindClientsPort findClientsPort;
    private final UpdateClientPort updateClientPort;
    private final ClientApiMapper clientApiMapper;

    @Operation(summary = "Create a new Client", tags = {"clients", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientRequest request) {
        try {
            log.info("Chegada" + request);
            Client client = clientApiMapper.fromRquest(request);
            Client saved = createClientPort.save(client);

            ClientResponse clientResponse = clientApiMapper.fromEntidy(saved);
            if (clientResponse == null) {
                return ResponseEntity.ok().build();
            }
            URI location = RestUtils.getUri(clientResponse.getId());

            return ResponseEntity.created(location).body(clientResponse);
        } catch (Exception ex) {
            log.info("Erro: " + ex.getMessage());
        }
        return null;
    }

    @Operation(summary = "Update a Client by Id", tags = {"clients", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ClientRequest request) {
        var client = clientApiMapper.fromRquest(request);
        Client updated = updateClientPort.update(id, client);

        ClientResponse clientResponse = clientApiMapper.fromEntidy(updated);
        if (clientResponse == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(clientResponse);
    }

    @Operation(summary = "Retrieve all Client", tags = {"clients", "get", "filter"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClientResponse>> findAll() {
        List<Client> clientList = findClientsPort.findAll();
        List<ClientResponse> clientResponse = clientApiMapper.map(clientList);
        return clientResponse.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(clientResponse);
    }

    @Operation(
            summary = "Retrieve a Client by Id",
            description = "Get a Client object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = {"clients", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ClientResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientResponse> findOne(@PathVariable("id") Long id) {
        Client clientSaved = findByIdClientPort.findById(id);
        if (clientSaved != null) {
            ClientResponse clientResponse = clientApiMapper.fromEntidy(clientSaved);
            return ResponseEntity.ok(clientResponse);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a Client by Id", tags = {"clienttrus", "delete"})
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = deleteClientPort.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }

    /**
     * This is an error handler specific to this controller. It overrides the global error handler.
     * In a real-world application, this particular combination of error handlers is almost
     * certainly undesirable: the global error handler returns a complex JSON object, while the
     * local error handler returns a string.
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var result = "There's a validation error in the client.";
        var errors = ex.getBindingResult().getAllErrors();
        var details = errors.stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return "\n" + fieldName + ":" + errorMessage;
        }).collect(Collectors.joining());
        return result;
    }
}