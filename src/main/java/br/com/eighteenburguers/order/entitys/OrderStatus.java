package br.com.eighteenburguers.order.entitys;

import java.util.stream.Stream;

public enum OrderStatus {

    CREATED(1, "Pedido criado"), 
    AWAITING_PAYMENT(2, "Aguardando pagamento"),
    PAID(3, "Pago"), 
    CANCELED(4, "Cancelado");

    private OrderStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    public static final OrderStatus ofCode(final Integer code) {
        return Stream.of(OrderStatus.values())
            .filter(item -> item.getCode().equals(code))
            .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
