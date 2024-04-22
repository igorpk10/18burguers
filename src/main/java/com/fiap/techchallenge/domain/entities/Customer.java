package com.fiap.techchallenge.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    public String cpf;
    public String name;
    public String email;
}
