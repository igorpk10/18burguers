package com.fiap.techchallenge.presentation.dtos;

import lombok.Data;

@Data
public class CustomerCreateRequestData {
    private String cpf;
    private String name;
    private String email;
}
