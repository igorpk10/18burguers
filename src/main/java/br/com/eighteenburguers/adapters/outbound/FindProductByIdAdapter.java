package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindProductByIdAdapter implements FindProductByIdOutputPort {
    @Override
    public Optional<Product> find(Long id) {
        return Optional.empty();
    }
}
