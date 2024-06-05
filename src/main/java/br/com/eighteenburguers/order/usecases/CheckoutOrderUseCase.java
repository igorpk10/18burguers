package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface CheckoutOrderUseCase {
    
    void execute(Long orderId) throws BusinessException;
}
