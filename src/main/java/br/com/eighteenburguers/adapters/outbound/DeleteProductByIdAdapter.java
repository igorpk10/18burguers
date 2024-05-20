package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.core.ports.outbound.product.DeleteProductByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductByIdAdapter implements DeleteProductByIdOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
