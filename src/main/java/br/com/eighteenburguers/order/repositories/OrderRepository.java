package br.com.eighteenburguers.order.repositories;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.order.entitys.OrderEntity;

public interface OrderRepository {
    
    List<OrderEntity> findAll();

    Optional<OrderEntity> findById(Long id);

    void save(OrderEntity entity);

}
