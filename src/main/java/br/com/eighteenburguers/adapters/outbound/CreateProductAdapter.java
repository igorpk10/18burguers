package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.CreateProductOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CreateProductAdapter implements CreateProductOutputPort {
    @Override
    public Product insert(Product product) {
        return null;
    }
}
