package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.product.mappers.ProductEntityMapper;
import br.com.eighteenburguers.product.entitys.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindProductByIdServiceImpl implements FindProductByIdService {

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
