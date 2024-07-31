package br.com.eighteenburguers.product.services;

import java.util.List;

import br.com.eighteenburguers.product.entitys.Product;

public interface FindProductService {
    
    List<Product> findAll();

    List<Product> findByIds(List<Long> ids);
}
