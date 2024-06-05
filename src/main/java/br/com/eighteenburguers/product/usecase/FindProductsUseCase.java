package br.com.eighteenburguers.product.usecase;

import java.util.List;

import br.com.eighteenburguers.product.entitys.Product;

public interface FindProductsUseCase {
    
    List<Product> execute();
}
