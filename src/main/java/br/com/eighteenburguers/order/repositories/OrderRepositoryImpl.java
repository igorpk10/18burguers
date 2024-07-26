package br.com.eighteenburguers.order.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.datasource.OrderDataSource;
import br.com.eighteenburguers.order.entitys.OrderEntity;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private OrderDataSource orderDataSource;

    public OrderRepositoryImpl(OrderDataSource orderDataSource) {
        this.orderDataSource = orderDataSource;
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        var response = orderDataSource.findById(id);
        return response;
    }

    @Override
    public List<OrderEntity> findAll() {
        var response = orderDataSource.findAll();
        return response;
    }

    @Override
    public void save(OrderEntity entity) {
        orderDataSource.save(entity);
    }
}
