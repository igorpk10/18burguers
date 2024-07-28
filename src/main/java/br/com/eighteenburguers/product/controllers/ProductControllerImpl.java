package br.com.eighteenburguers.product.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.product.dtos.ProductRequest;
import br.com.eighteenburguers.product.dtos.ProductResponse;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.mappers.ProductMapper;
import br.com.eighteenburguers.product.usecase.CreateProductUseCase;
import br.com.eighteenburguers.product.usecase.DeleteProductByIdUseCase;
import br.com.eighteenburguers.product.usecase.FindProductByCategoryUseCase;
import br.com.eighteenburguers.product.usecase.FindProductByIdUseCase;
import br.com.eighteenburguers.product.usecase.FindProductsUseCase;
import br.com.eighteenburguers.product.usecase.UpdateProductUseCase;

@Component
public class ProductControllerImpl implements ProductController {

    private final CreateProductUseCase createProductUseCase;

    private final UpdateProductUseCase updateProductUseCase;

    private final FindProductByIdUseCase findProductByIdUseCase;

    private final FindProductByCategoryUseCase findProductByCategoryUseCase;

    private final DeleteProductByIdUseCase deleteProductByIdUseCase;

    private final FindProductsUseCase findProductsUseCase;

    private final ProductMapper productMapper;

    public ProductControllerImpl(
            CreateProductUseCase createProductUseCase,
            UpdateProductUseCase updateProductUseCase,
            FindProductByIdUseCase findProductByIdUseCase,
            FindProductByCategoryUseCase findProductByCategoryUseCase,
            DeleteProductByIdUseCase deleteProductByIdUseCase,
            FindProductsUseCase findProductsUseCase,
            ProductMapper productMapper) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.findProductByCategoryUseCase = findProductByCategoryUseCase;
        this.deleteProductByIdUseCase = deleteProductByIdUseCase;
        this.findProductsUseCase = findProductsUseCase;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse insert(ProductRequest productRequest) throws BusinessException {
        var product = productMapper.toProduct(productRequest);
        var productPersisted = createProductUseCase.insert(product);
        return productMapper.toProductResponse(productPersisted);
    }

    @Override
    public List<ProductResponse> fetchAll() {
        var productList = findProductsUseCase.execute();
        return productMapper.toListResponse(productList);
    }

    @Override
    public ProductResponse fetchProductById(Long id) throws BusinessException {
        var product = findProductByIdUseCase.find(id);
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> fetchByCategory(Integer categoryId) throws BusinessException {
        var productList = findProductByCategoryUseCase.find(categoryId);
        return productMapper.toListResponse(productList);
    }

    @Override
    public ProductResponse editProduct(Long id, ProductRequest productRequest) throws BusinessException {
        Product product = productMapper.toProduct(productRequest);
        product.setId(id);
        updateProductUseCase.update(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) throws BusinessException {
        deleteProductByIdUseCase.delete(id);
    }
}
