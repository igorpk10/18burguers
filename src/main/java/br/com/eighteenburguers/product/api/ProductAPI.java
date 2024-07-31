package br.com.eighteenburguers.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.configuration.ErrorResponses;
import br.com.eighteenburguers.product.controllers.ProductController;
import br.com.eighteenburguers.product.dtos.ProductRequest;
import br.com.eighteenburguers.product.dtos.ProductResponse;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Products")
@Slf4j
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductAPI {

    @Autowired
    private ProductController productController;

    @PostMapping
    @Transactional
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Product.class)))
    public ResponseEntity<?> insert(@Valid @RequestBody ProductRequest productRequest) {
        try {
            var response =  productController.insert(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BusinessException e) {
            log.error("Error when trying to create product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    public ResponseEntity<?> list() {
        try {
            var products = productController.fetchAll();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class)))
    public ResponseEntity<?> findById(@PathVariable final Long id) {
        try {
            var product = productController.fetchProductById(id);
            return ResponseEntity.ok().body(product);
        } catch (BusinessException e) {
            log.error("Error when trying to find product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/category/{categoryId}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    public ResponseEntity<List<ProductResponse>> findByCategory(@PathVariable final String categoryId) {
        try {
            var productList = productController.fetchByCategory(Integer.parseInt(categoryId));
            log.info("Size: {}", productList.size());
            return ResponseEntity.ok().body(productList);
        } catch (BusinessException e) {
            log.error("Error when trying to find products: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody ProductRequest productRequest) {
        try {
            var response = productController.editProduct(id, productRequest);
            return ResponseEntity.ok().body(response);
        } catch (BusinessException e) {
            log.error("Error when trying to update product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ErrorResponses(e));
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            productController.deleteProduct(id);
            return ResponseEntity.ok().body(null);
        } catch (BusinessException e) {
            log.error("Error when trying to delete product: {}: {}", e.getCode(),e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }
}
