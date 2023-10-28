package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.RestaurantRequest;
import br.com.postech.sevenfood.application.api.dto.response.RestaurantResponse;
import br.com.postech.sevenfood.application.api.mappper.RestaurantApiMapper;
import br.com.postech.sevenfood.commons.util.RestUtils;
import br.com.postech.sevenfood.core.domain.Restaurant;
import br.com.postech.sevenfood.core.ports.in.restaurant.*;
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
@RequestMapping("/v1/restaurants")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class RestaurantResources {

    private final CreateRestaurantPort createRestaurantPort;
    private final DeleteRestaurantPort deleteRestaurantPort;
    private final FindByIdRestaurantPort findByIdRestaurantPort;
    private final FindRestaurantsPort findRestaurantsPort;
    private final UpdateRestaurantPort updateRestaurantPort;
    private final RestaurantApiMapper restaurantApiMapper;

    @Operation(summary = "Create a new Restaurant", tags = {"restaurants", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = RestaurantResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<RestaurantResponse> save(@Valid @RequestBody RestaurantRequest request) {
        try {
            log.info("Chegada" + request);
            Restaurant restaurant = restaurantApiMapper.fromRquest(request);
            Restaurant saved = createRestaurantPort.save(restaurant);

            RestaurantResponse restaurantResponse = restaurantApiMapper.fromEntidy(saved);
            if (restaurantResponse == null) {
                return ResponseEntity.ok().build();
            }
            URI location = RestUtils.getUri(restaurantResponse.getId());

            return ResponseEntity.created(location).body(restaurantResponse);
        } catch (Exception ex) {
            log.info("Erro: " + ex.getMessage());
        }
        return null;
    }

    @Operation(summary = "Update a Restaurant by Id", tags = {"restaurants", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = RestaurantResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantResponse> update(@PathVariable("id") Long id, @Valid @RequestBody RestaurantRequest request) {
        var restaurant = restaurantApiMapper.fromRquest(request);
        Restaurant updated = updateRestaurantPort.update(id, restaurant);

        RestaurantResponse restaurantResponse = restaurantApiMapper.fromEntidy(updated);
        if (restaurantResponse == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(restaurantResponse);
    }

    @Operation(summary = "Retrieve all Restaurant", tags = {"restaurants", "get", "filter"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = RestaurantResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RestaurantResponse>> findAll() {
        List<Restaurant> restaurantList = findRestaurantsPort.findAll();
        List<RestaurantResponse> restaurantResponse = restaurantApiMapper.map(restaurantList);
        return restaurantResponse.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(restaurantResponse);
    }

    @Operation(
            summary = "Retrieve a Restaurant by Id",
            description = "Get a Restaurant object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = {"restaurants", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = RestaurantResources.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestaurantResponse> findOne(@PathVariable("id") Long id) {
        Restaurant restaurantSaved = findByIdRestaurantPort.findById(id);
        if (restaurantSaved != null) {
            RestaurantResponse restaurantResponse = restaurantApiMapper.fromEntidy(restaurantSaved);
            return ResponseEntity.ok(restaurantResponse);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a Restaurant by Id", tags = {"restauranttrus", "delete"})
    @ApiResponses({@ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = deleteRestaurantPort.remove(id);
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
        var result = "There's a validation error in the restaurant.";
        var errors = ex.getBindingResult().getAllErrors();
        var details = errors.stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return "\n" + fieldName + ":" + errorMessage;
        }).collect(Collectors.joining());
        return result;
    }
}