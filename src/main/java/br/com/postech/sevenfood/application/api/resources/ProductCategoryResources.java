package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.ProductCategoryRequest;
import br.com.postech.sevenfood.application.api.dto.response.ProductCategoryResponse;
import br.com.postech.sevenfood.application.api.mappper.ProductCategoryApiMapper;
import br.com.postech.sevenfood.commons.util.RestUtils;
import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.ports.in.productcategory.*;
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
@RequestMapping("/v1/product-categories")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ProductCategoryResources {

    private final CreateProductCategoryPort createProductCategoryPort;
    private final DeleteProductCategoryPort deleteProductCategoryPort;
    private final FindByIdProductCategoryPort findByIdProductCategoryPort;
    private final FindProductCategoriesPort findProductCategoriesPort;
    private final UpdateProductCategoryPort updateProductCategoryPort;
    private final ProductCategoryApiMapper productCategoryApiMapper;

    @Operation(summary = "Create a new ProductCategory", tags = {"productCategorys", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ProductCategoryResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<ProductCategoryResponse> save(@Valid @RequestBody ProductCategoryRequest request) {
        try {
            log.info("Chegada" + request);
            ProductCategory productCategory = productCategoryApiMapper.fromRquest(request);
            ProductCategory saved = createProductCategoryPort.save(productCategory);

            ProductCategoryResponse productCategoryResponse = productCategoryApiMapper.fromEntidy(saved);
            if (productCategoryResponse == null) {
                return ResponseEntity.ok().build();
            }
            URI location = RestUtils.getUri(productCategoryResponse.getId());

            return ResponseEntity.created(location).body(productCategoryResponse);
        } catch (Exception ex) {
            log.info("Erro: " + ex.getMessage());
        }
        return null;
    }

    @Operation(summary = "Update a ProductCategory by Id", tags = {"productCategorys", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductCategoryResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductCategoryResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ProductCategoryRequest request) {
        var productCategory = productCategoryApiMapper.fromRquest(request);
        ProductCategory updated = updateProductCategoryPort.update(id, productCategory);

        ProductCategoryResponse productCategoryResponse = productCategoryApiMapper.fromEntidy(updated);
        if (productCategoryResponse == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(productCategoryResponse);
    }

    @Operation(summary = "Retrieve all ProductCategory", tags = {"productCategorys", "get", "filter"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductCategoryResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductCategoryResponse>> findAll() {
        List<ProductCategory> productCategoryList = findProductCategoriesPort.findAll();
        List<ProductCategoryResponse> productCategoryResponse = productCategoryApiMapper.map(productCategoryList);
        return productCategoryResponse.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(productCategoryResponse);
    }

    @Operation(
            summary = "Retrieve a ProductCategory by Id",
            description = "Get a ProductCategory object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = {"productCategorys", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProductCategoryResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductCategoryResponse> findOne(@PathVariable("id") Long id) {
        ProductCategory productCategorySaved = findByIdProductCategoryPort.findById(id);
        if (productCategorySaved != null) {
            ProductCategoryResponse productCategoryResponse = productCategoryApiMapper.fromEntidy(productCategorySaved);
            return ResponseEntity.ok(productCategoryResponse);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a ProductCategory by Id", tags = {"productCategorytrus", "delete"})
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = deleteProductCategoryPort.remove(id);
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
        var result = "There's a validation error in the productCategory.";
        var errors = ex.getBindingResult().getAllErrors();
        var details = errors.stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return "\n" + fieldName + ":" + errorMessage;
        }).collect(Collectors.joining());
        return result;
    }
}