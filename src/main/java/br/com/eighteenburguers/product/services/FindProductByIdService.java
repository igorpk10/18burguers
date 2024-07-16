package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.entitys.Product;

import java.util.Optional;

public interface FindProductByIdService {

    Optional<Product> find(Long id);
}
