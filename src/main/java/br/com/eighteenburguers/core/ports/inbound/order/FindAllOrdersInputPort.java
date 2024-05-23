package br.com.eighteenburguers.core.ports.inbound.order;

import java.util.List;

import br.com.eighteenburguers.core.domain.Order;

public interface FindAllOrdersInputPort {

    List<Order> execute();
}
