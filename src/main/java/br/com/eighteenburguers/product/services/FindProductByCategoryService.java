package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.entitys.Product;

import java.util.List;

public interface FindProductByCategoryService {

    List<Product> find(int codigo);
}
