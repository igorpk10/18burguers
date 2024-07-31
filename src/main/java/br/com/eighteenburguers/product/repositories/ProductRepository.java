package br.com.eighteenburguers.product.repositories;

import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.category.model.Category;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository{

    void save(ProductEntity entity);

    void deleteById(Long id);

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategory(Category ofCodigo);

    List<ProductEntity> findAll();
}
