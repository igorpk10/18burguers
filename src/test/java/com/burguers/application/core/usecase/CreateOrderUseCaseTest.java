package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.category.model.Category;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCase;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.product.services.FindProductService;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {
    
    @Mock
    FindProductService findProductOutputPort;
    
    @Mock
    SaveOrderService saveOrderOutputPort;

    Faker faker;

    @BeforeEach
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeCreateOrder() throws BusinessException {
    	final String orderId = UUID.randomUUID().toString();
    	
        Mockito.when(findProductOutputPort.findByIds(Mockito.anyList())).thenReturn(mockProducts());
        Mockito.when(saveOrderOutputPort.save(Mockito.any())).thenReturn(new Order(orderId, null));

        CreateOrderUseCase usecase = new CreateOrderUseCaseImpl(findProductOutputPort, saveOrderOutputPort);
        Order order = usecase.execute(orderId, List.of(new OrderItem(mockProduct(), 1, null)));
        assertNotNull(order);
        Mockito.verify(saveOrderOutputPort, Mockito.times(1)).save(Mockito.any());
    }

    List<Product> mockProducts() {
        return List.of(mockProduct());
    }

    Product mockProduct() {
        Product product = new Product(faker.commerce().productName(), Category.LANCHE, BigDecimal.ZERO, "", "");
        product.setId(1L);
        return product;
    }
}
