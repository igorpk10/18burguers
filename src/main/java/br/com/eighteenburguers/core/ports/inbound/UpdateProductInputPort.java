package br.com.eighteenburguers.core.ports.inbound;

import br.com.eighteenburguers.core.domain.Product;

public interface UpdateProductInputPort {

    void update(Product product);
}
