package br.com.eighteenburguers.core.usecase.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.domain.OrderStatus;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.order.CreateOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderUseCase implements CreateOrderInputPort {

    private final FindProductOutputPort findProductOutputPort;
    private final SaveOrderOutputPort saveOrderOutputPort;

    @Override
    public Order execute(final Long customerId, final List<OrderItem> items) throws BusinessException {

        List<OrderItem> orderItems = this.mapProducts(items);

        Order order = new Order(customerId, orderItems);
        order.calculateAmount();
        order.setStatus(OrderStatus.AWAITING_PAYMENT);
        return saveOrderOutputPort.save(order);
    }

    private List<OrderItem> mapProducts(List<OrderItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();

        List<Long> ids = items.stream().map(OrderItem::getProduct)
            .map(Product::getId)    
            .collect(Collectors.toList());

        List<Product> products = findProductOutputPort.findByIds(ids);

        for(OrderItem item: items) {
            Optional<Product> optional = products.stream()
                .filter(product -> product.getId().equals(item.getProduct().getId()))
                .findFirst();
            
            if(optional.isPresent()) {
                orderItems.add(new OrderItem(optional.get(), item.getQuantity(), item.getObservation()));
            }
        }

        return orderItems;
    }

}
