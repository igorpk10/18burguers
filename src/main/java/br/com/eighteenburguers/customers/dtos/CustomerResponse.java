package br.com.eighteenburguers.customers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
    
    private String id;
    private String name;
    private String cpf;
    private String email;
}
