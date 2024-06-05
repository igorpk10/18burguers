package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.product.mappers.ProductEntityMapper;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindProductByCategoryServiceImpl implements FindProductByCategoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public List<Product> find(int codigo) {

        List<ProductEntity> productList = productRepository.findByCategory(Category.ofCodigo(codigo));
        return productList.stream().map(productEntityMapper::toProduct).collect(Collectors.toList());
    }
}
