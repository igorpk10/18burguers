package br.com.eighteenburguers.core.ports.inbound.order;

import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface CheckoutOrderInputPort {
    
    void execute(Long orderId) throws BusinessException;
}
