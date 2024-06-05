package br.com.eighteenburguers.product.controllers;

import br.com.eighteenburguers.application.ApiV1;
import br.com.eighteenburguers.product.mappers.ProductMapper;
import br.com.eighteenburguers.product.dtos.ProductRequest;
import br.com.eighteenburguers.configuration.ErrorResponses;
import br.com.eighteenburguers.product.dtos.ProductResponse;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.usecase.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products")
@Slf4j
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController implements ApiV1 {

    private final CreateProductUseCase createProductInputPort;

    private final UpdateProductUseCase updateProductInputPort;

    private final FindProductByIdUseCase findProductByIdInputPort;

    private final FindProductByCategoryUseCase findProductByCategoryInputPort;

    private final DeleteProductByIdUseCase deleteProductByIdInputPort;
    
    private final FindProductsUseCase findProductsInputPort;

    private final ProductMapper productMapper;

    @PostMapping
    @Transactional
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Product.class)))
    public ResponseEntity<?> insert(@Valid @RequestBody ProductRequest productRequest) {
        try {
            var product = productMapper.toProduct(productRequest);
            log.info("ProductID: {}", product.getId());
            Product productPersisted = createProductInputPort.insert(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductResponse(productPersisted));
        } catch (BusinessException e) {
            log.error("Error when trying to create product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    public ResponseEntity<?> list() {
        List<Product> products = findProductsInputPort.execute();    
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Product.class)))
    public ResponseEntity<ProductResponse> findById(@PathVariable final Long id) {
        try {
            Product product = findProductByIdInputPort.find(id);
            return ResponseEntity.ok().body(productMapper.toProductResponse(product));
        } catch (BusinessException e) {
            log.error("Error when trying to find product: {}: {}", e.getCode(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/category/{categoryId}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    public ResponseEntity<List<ProductResponse>> findByCategory(@PathVariable final String categoryId) {
        try {
            List<Product> productList = findProductByCategoryInputPort.find(Integer.parseInt(categoryId));
            log.info("Size: {}", productList.size());
            List<ProductResponse> productResponseList = productList.stream().map(productMapper::toProductResponse).toList();
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error when trying to find products: {}: {}", e.getCode(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> update(@PathVariable final String id, @Valid @RequestBody ProductRequest productRequest) {
        try {
            Product product = productMapper.toProduct(productRequest);
            product.setId(Long.parseLong(id));
            updateProductInputPort.update(product);
            return ResponseEntity.noContent().build();
        } catch (BusinessException e) {
            log.error("Error when trying to update product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> delete(@PathVariable final String id) {
        try {
            deleteProductByIdInputPort.delete(Long.parseLong(id));
            return ResponseEntity.noContent().build();
        } catch (BusinessException e) {
            log.error("Error when trying to delete product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

}
