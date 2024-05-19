package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByCategoryOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductByCategoryAdapter implements FindProductByCategoryOutputPort {
    @Override
    public List<Product> find(int codigo) {
        return null;
    }
}
