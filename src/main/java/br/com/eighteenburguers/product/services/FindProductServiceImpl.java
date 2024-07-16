package br.com.eighteenburguers.product.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.product.mappers.ProductEntityMapper;
import br.com.eighteenburguers.product.entitys.Product;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindProductServiceImpl implements FindProductService {

    private final ProductRepository repository;
    private final ProductEntityMapper mapper;

    @Override
    public List<Product> findByIds(List<Long> ids) {    
        List<ProductEntity> products = ids.stream().map(id -> repository.findById(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
        .collect(Collectors.toList());

        return mapper.toProduct(products);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = repository.findAll();
        return mapper.toProduct(entities);
    }
    
}
