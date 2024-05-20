package br.com.eighteenburguers.core.ports.outbound.product;

import br.com.eighteenburguers.core.domain.Product;

import java.util.List;

public interface FindProductByCategoryOutputPort {

    List<Product> find(int codigo);
}
