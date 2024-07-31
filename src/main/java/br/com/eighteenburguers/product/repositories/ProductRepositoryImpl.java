package br.com.eighteenburguers.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.category.model.Category;
import br.com.eighteenburguers.product.datasource.ProductDataSource;
import br.com.eighteenburguers.product.entitys.ProductEntity;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductDataSource dataSource;

    public ProductRepositoryImpl(ProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(ProductEntity entity){
        dataSource.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        dataSource.deleteById(id);
    }

    @Override
    public List<ProductEntity> findByCategory(Category ofCodigo) {
        return dataSource.findByCategory(ofCodigo);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return dataSource.findById(id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return dataSource.findAll();
    }
    
}
