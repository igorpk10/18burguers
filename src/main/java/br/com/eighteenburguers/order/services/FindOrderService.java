package br.com.eighteenburguers.order.services;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.order.entitys.Order;

public interface FindOrderService {
    
    Optional<Order> findById(Long id);

    List<Order> findAll();
}
