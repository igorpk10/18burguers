package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface FindOrderByIdUseCase {
    
    Order execute(Long orderId) throws BusinessException;
}
