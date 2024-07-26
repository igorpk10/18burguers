package br.com.eighteenburguers.order.datasource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.entitys.OrderEntity;
import br.com.eighteenburguers.order.entitys.OrderStatus;

@Repository
public interface OrderDataSource extends JpaRepository<OrderEntity, Long> {
    
    
    List<OrderEntity> findAllByStatusNotOrderByStatusAscCreatedAtAsc(OrderStatus status);
}
