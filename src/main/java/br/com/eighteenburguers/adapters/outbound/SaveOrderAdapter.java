package br.com.eighteenburguers.adapters.outbound;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.adapters.outbound.repository.OrderRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.order.OrderEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.OrderEntityMapper;
import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveOrderAdapter implements SaveOrderOutputPort {

    private final OrderRepository repository;
    private final OrderEntityMapper mapper;

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        repository.save(entity);
        order.setId(entity.getId());
        order.setCreatedAt(entity.getCreatedAt());
        order.setUpdatedAt(entity.getUpdatedAt());
        return order;
    }
    
}
