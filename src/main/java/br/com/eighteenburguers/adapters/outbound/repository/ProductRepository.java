package br.com.eighteenburguers.adapters.outbound.repository;

import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByCategory(int categoryId);
}
