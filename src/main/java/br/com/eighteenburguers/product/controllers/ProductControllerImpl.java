package br.com.eighteenburguers.product.controllers;

import java.util.List;

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

public class ProductControllerImpl implements ProductController {

    private final CreateProductUseCase createProductInputPort;

    private final UpdateProductUseCase updateProductInputPort;

    private final FindProductByIdUseCase findProductByIdInputPort;

    private final FindProductByCategoryUseCase findProductByCategoryInputPort;

    private final DeleteProductByIdUseCase deleteProductByIdInputPort;

    private final FindProductsUseCase findProductsInputPort;

    private final ProductMapper productMapper;

    public ProductControllerImpl(CreateProductUseCase createProductInputPort, UpdateProductUseCase updateProductInputPort,
            FindProductByIdUseCase findProductByIdInputPort,
            FindProductByCategoryUseCase findProductByCategoryInputPort,
            DeleteProductByIdUseCase deleteProductByIdInputPort, FindProductsUseCase findProductsInputPort,
            ProductMapper productMapper) {
        this.createProductInputPort = createProductInputPort;
        this.updateProductInputPort = updateProductInputPort;
        this.findProductByIdInputPort = findProductByIdInputPort;
        this.findProductByCategoryInputPort = findProductByCategoryInputPort;
        this.deleteProductByIdInputPort = deleteProductByIdInputPort;
        this.findProductsInputPort = findProductsInputPort;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse insert(ProductRequest productRequest) throws BusinessException {
        var product = productMapper.toProduct(productRequest);
        var productPersisted = createProductInputPort.insert(product);
        return productMapper.toProductResponse(productPersisted);
    }

    @Override
    public List<ProductResponse> fetchAll() {
        var productList = findProductsInputPort.execute();
        return productMapper.toListResponse(productList);
    }

    @Override
    public ProductResponse fetchProductById(Long id) throws BusinessException {
        var product = findProductByIdInputPort.find(id);
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> fetchByCategory(Integer categoryId) throws BusinessException {
        var productList = findProductByCategoryInputPort.find(categoryId);
        return productMapper.toListResponse(productList);
    }

    @Override
    public ProductResponse editProduct(Long id, ProductRequest productRequest) throws BusinessException {
        Product product = productMapper.toProduct(productRequest);
        product.setId(id);
        updateProductInputPort.update(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) throws BusinessException {
        deleteProductByIdInputPort.delete(id);
    }
}
