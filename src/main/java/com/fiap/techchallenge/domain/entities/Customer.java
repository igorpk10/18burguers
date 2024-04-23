package com.fiap.techchallenge.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private int id;
    private String cpf;
    private String name;
    private String email;
}
