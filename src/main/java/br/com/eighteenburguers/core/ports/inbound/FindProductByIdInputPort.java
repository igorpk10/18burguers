package br.com.eighteenburguers.core.ports.inbound;

import br.com.eighteenburguers.core.domain.Product;

import java.util.Optional;

public interface FindProductByIdInputPort {

    Product find(Long id);
}
