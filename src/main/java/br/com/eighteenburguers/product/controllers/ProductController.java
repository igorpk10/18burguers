package br.com.eighteenburguers.product.controllers;

import java.util.List;

import br.com.eighteenburguers.product.dtos.ProductRequest;
import br.com.eighteenburguers.product.dtos.ProductResponse;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface ProductController {
    
     public ProductResponse insert(ProductRequest productRequest) throws BusinessException;

     public List<ProductResponse> fetchAll();

     public ProductResponse fetchProductById(Long id) throws BusinessException;

     public List<ProductResponse> fetchByCategory(Integer categoryId) throws BusinessException;

     public ProductResponse editProduct(Long id,  ProductRequest productRequest) throws BusinessException;

     public void deleteProduct(Long id) throws BusinessException;

}
