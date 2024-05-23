package br.com.eighteenburguers.core.ports.outbound.product;

import java.util.List;

import br.com.eighteenburguers.core.domain.Product;

public interface FindProductOutputPort {
    
    List<Product> findByIds(List<Long> ids);
}
