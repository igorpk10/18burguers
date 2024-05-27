package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.ProductEntityMapper;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindProductByIdAdapter implements FindProductByIdOutputPort {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> find(Long id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if(optional.isEmpty()) {
            return Optional.empty();
        }
        Product product = productEntityMapper.toProduct(optional.get());
        return Optional.of(product);
    }
}
