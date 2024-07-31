package br.com.eighteenburguers.order.services;

import br.com.eighteenburguers.order.entitys.Order;

public interface SaveOrderService {
    
    Order save(Order order);
}
