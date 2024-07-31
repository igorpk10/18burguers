package br.com.eighteenburguers.order.usecases;

import java.util.List;

import br.com.eighteenburguers.order.entitys.Order;

public interface FindAllOrdersUseCase {

    List<Order> execute();
}
