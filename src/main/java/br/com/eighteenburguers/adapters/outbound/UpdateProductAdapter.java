package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.UpdateProductOutputPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductAdapter implements UpdateProductOutputPort {
    @Override
    public Product update(Product product) {
        return null;
    }
}
