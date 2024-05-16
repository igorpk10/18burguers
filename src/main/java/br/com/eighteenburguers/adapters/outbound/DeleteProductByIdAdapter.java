package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.DeleteProductByIdOutputPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductByIdAdapter implements DeleteProductByIdOutputPort {
    @Override
    public Product delete(Long id) {
        return null;
    }
}
