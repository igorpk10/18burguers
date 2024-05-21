package br.com.eighteenburguers.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    
    private Long id;
    private Long customerId;
    private List<OrderItem> items;
    private BigDecimal amount = new BigDecimal(0);

    public Order(Long id, Long customerId, List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public Order(Long customerId, List<OrderItem> items) {
        this.items = items;
        if(customerId == null) {
            this.customerId = 0L;
        } else {
            this.customerId = customerId;
        }
    }

    public Long getId() {
        return id;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void calculateAmount() {
        this.amount = this.items.stream()
            .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}
