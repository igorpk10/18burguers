package com.fiap.techchallenge.presentation.dtos;

import lombok.Data;

@Data
public class CustomerGetResponseData {
    public int id;
    public String cpf;
    public String name;
    public String email;
}
