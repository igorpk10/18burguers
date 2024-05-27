package br.com.eighteenburguers.core.ports.outbound.order;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.core.domain.Order;

public interface FindOrderOutputPort {
    
    Optional<Order> findById(Long id);

    List<Order> findAll();
}
