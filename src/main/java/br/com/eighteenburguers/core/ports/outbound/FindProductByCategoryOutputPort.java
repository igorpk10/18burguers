package br.com.eighteenburguers.core.ports.outbound;

import br.com.eighteenburguers.core.domain.Product;

public interface FindProductByCategoryOutputPort {

    Product find(int codigo);
}