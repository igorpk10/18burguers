package br.com.eighteenburguers.product.repositories;

import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.category.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategory(Category category);

}
