package br.com.eighteenburguers.core.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Order {
    
    private Long id;
    private Long customerId;
    private BigDecimal amount = BigDecimal.ZERO;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<OrderItem> items;

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

    public Order(Long customerId, List<OrderItem> items, OrderStatus status) {
        this.items = items;
        this.status = status;
        if(customerId == null) {
            this.customerId = 0L;
        } else {
            this.customerId = customerId;
        }
    }

    public Order(Long id, Long customerId, List<OrderItem> items, BigDecimal amount, OrderStatus status,
            Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void calculateAmount() {
        this.amount = this.items.stream()
            .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}
