package br.com.eighteenburguers.core.ports.inbound.product;

import java.util.List;

import br.com.eighteenburguers.core.domain.Product;

public interface FindProductsInputPort {
    
    List<Product> execute();
}
