package br.com.eighteenburguers.core.ports.outbound;

import br.com.eighteenburguers.core.domain.Product;

public interface UpdateProductOutputPort {

    Product update(Product product);
}
