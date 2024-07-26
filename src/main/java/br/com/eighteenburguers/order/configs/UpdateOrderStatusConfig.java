package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.UpdateOrderStatus;
import br.com.eighteenburguers.order.usecases.UpdateOrderStatusImpl;

@Configuration
public class UpdateOrderStatusConfig {

	@Bean
	UpdateOrderStatus updateOrderStatus(FindOrderService findOrderService, SaveOrderService saveOrderService) {
		return new UpdateOrderStatusImpl(findOrderService, saveOrderService);
	}
}
