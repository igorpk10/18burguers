package br.com.eighteenburguers.adapters.inbound.controller;

import br.com.eighteenburguers.adapters.inbound.controller.mappers.ProductMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.ProductRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ErrorResponses;
import br.com.eighteenburguers.adapters.inbound.controller.response.ProductResponse;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products")
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController implements ApiV1 {

    @Autowired
    private CreateProductInputPort createProductInputPort;

    @Autowired
    private UpdateProductInputPort updateProductInputPort;

    @Autowired
    private FindProductByIdInputPort findProductByIdInputPort;

    @Autowired
    private FindProductByCategoryInputPort findProductByCategoryInputPort;

    @Autowired
    private DeleteProductByIdInputPort deleteProductByIdInputPort;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping
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
