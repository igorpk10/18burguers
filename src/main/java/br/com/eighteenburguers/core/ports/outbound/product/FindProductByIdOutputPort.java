package br.com.eighteenburguers.core.ports.outbound.product;

import br.com.eighteenburguers.core.domain.Product;

import java.util.Optional;

public interface FindProductByIdOutputPort {

    Optional<Product> find(Long id);
}
