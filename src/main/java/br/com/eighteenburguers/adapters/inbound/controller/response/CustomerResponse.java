package br.com.eighteenburguers.adapters.inbound.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    
    private Long id;
    private String name;
    private String cpf;
    private String email;
}
