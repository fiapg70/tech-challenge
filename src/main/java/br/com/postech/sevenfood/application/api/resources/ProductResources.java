package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.ProductRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductResponse;
import br.com.postech.sevenfood.application.api.mappper.ProductApiMapper;
import br.com.postech.sevenfood.commons.util.RestUtils;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.ports.in.product.*;
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
@RequestMapping("/v1/products")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ProductResources {

    private final CreateProductPort createProductPort;
    private final DeleteProductPort deleteProductPort;
    private final FindByIdProductPort findByIdProductPort;
    private final FindProductsPort findProductsPort;
    private final UpdateProductPort updateProductPort;
    private final ProductApiMapper productApiMapper;

    @Operation(summary = "Create a new Product", tags = {"products", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ProductResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        try {
            log.info("Chegada" + request);
            Product product = productApiMapper.fromRquest(request);
            Product saved = createProductPort.save(product);

            ProductResponse productResponse = productApiMapper.fromEntidy(saved);
            if (productResponse == null) {
                return ResponseEntity.ok().build();
            }
            URI location = RestUtils.getUri(productResponse.getId());

            return ResponseEntity.created(location).body(productResponse);
        } catch (Exception ex) {
            log.info("Erro: " + ex.getMessage());
        }
        return null;
    }

    @Operation(summary = "Update a Product by Id", tags = {"products", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ProductRequest request) {
        var product = productApiMapper.fromRquest(request);
        Product updated = updateProductPort.update(id, product);

        ProductResponse productResponse = productApiMapper.fromEntidy(updated);
        if (productResponse == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(productResponse);
    }

    @Operation(summary = "Retrieve all Product", tags = {"products", "get", "filter"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> productList = findProductsPort.findAll();
        List<ProductResponse> productResponse = productApiMapper.map(productList);
        return productResponse.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(productResponse);
    }

    @Operation(
            summary = "Retrieve a Product by Id",
            description = "Get a Product object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = {"products", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> findOne(@PathVariable("id") Long id) {
        Product productSaved = findByIdProductPort.findById(id);
        if (productSaved != null) {
            ProductResponse productResponse = productApiMapper.fromEntidy(productSaved);
            return ResponseEntity.ok(productResponse);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a Product by Id", tags = {"producttrus", "delete"})
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = deleteProductPort.remove(id);
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
        var result = "There's a validation error in the product.";
        var errors = ex.getBindingResult().getAllErrors();
        var details = errors.stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return "\n" + fieldName + ":" + errorMessage;
        }).collect(Collectors.joining());
        return result;
    }
}