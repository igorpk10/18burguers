package br.com.eighteenburguers.adapters.inbound.controller;

import br.com.eighteenburguers.adapters.inbound.controller.mappers.ProductMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.ProductRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ErrorResponses;
import br.com.eighteenburguers.adapters.inbound.controller.response.ProductResponse;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

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
    public ResponseEntity<?> insert(@Valid @RequestBody ProductRequest productRequest) {
        try {
            var product = productMapper.toProduct(productRequest);
            createProductInputPort.insert(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductResponse(product));
        } catch (BusinessException e) {
            log.error("Error when trying to create product: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable final String id) {
        try {
            Product product = findProductByIdInputPort.find(Long.parseLong(id));
            return ResponseEntity.ok().body(productMapper.toProductResponse(product));
        } catch (BusinessException e) {
            log.error("Error when trying to find product: {}: {}", e.getCode(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findByCategory(@PathVariable final String categoryId) {
        try {
            List<Product> productList = findProductByCategoryInputPort.find(Integer.parseInt(categoryId));
            List<ProductResponse> productResponseList = productList.stream().map(productMapper::toProductResponse).toList();
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error when trying to find products: {}: {}", e.getCode(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
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
