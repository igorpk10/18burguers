package br.com.eighteenburguers.product.datasource;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.category.model.Category;
import br.com.eighteenburguers.product.entitys.ProductEntity;

@Repository
public interface ProductDataSource extends JpaRepository<ProductEntity, Long> {
    
    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategory(Category category);

}
