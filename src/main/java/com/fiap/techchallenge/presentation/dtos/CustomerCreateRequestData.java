package com.fiap.techchallenge.presentation.dtos;

import lombok.Data;

@Data
public class CustomerCreateRequestData {
    public String cpf;
    public String name;
    public String email;
}
