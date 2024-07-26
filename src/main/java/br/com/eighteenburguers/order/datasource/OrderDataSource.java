package br.com.eighteenburguers.order.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.entitys.OrderEntity;

@Repository
public interface OrderDataSource extends JpaRepository<OrderEntity, Long> {
    
}
