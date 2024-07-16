package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import br.com.eighteenburguers.product.mappers.ProductEntityMapper;
import br.com.eighteenburguers.product.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductServiceImpl implements UpdateProductService {

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
