package br.com.eighteenburguers.adapters.outbound.repository;

import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.core.enums.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategory(Category category);
    // List<ProductEntity> findByCategory(int categoryId);

}
