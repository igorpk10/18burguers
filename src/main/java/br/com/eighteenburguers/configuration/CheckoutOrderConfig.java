package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.core.ports.inbound.order.CheckoutOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.CheckoutOrderUseCase;

@Configuration
public class CheckoutOrderConfig {

    @Bean
    CheckoutOrderInputPort checkoutOrderUseCase(SaveOrderOutputPort saveOrderOutputPort, FindOrderOutputPort findOrderOutputPort) {
        return new CheckoutOrderUseCase(saveOrderOutputPort, findOrderOutputPort);
    }
}
