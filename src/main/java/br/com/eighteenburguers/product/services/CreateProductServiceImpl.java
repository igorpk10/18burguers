package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.product.mappers.ProductEntityMapper;
import br.com.eighteenburguers.product.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductServiceImpl implements CreateProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public Product insert(Product product) {
        ProductEntity entity = productEntityMapper.toEntity(product);
        productRepository.save(entity);
        return productEntityMapper.toProduct(entity);
    }
}
