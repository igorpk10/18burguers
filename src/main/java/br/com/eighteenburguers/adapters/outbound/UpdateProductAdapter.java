package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.ProductEntityMapper;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.UpdateProductOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductAdapter implements UpdateProductOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public void update(Product product) {
        var productEntity = productEntityMapper.toEntity(product);
        productRepository.save(productEntity);
    }
}
