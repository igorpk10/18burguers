package com.fiap.techchallenge.presentation.dtos;

import lombok.Data;

@Data
public class CustomerCreateResponseData {
    int id;
    String message = "customer created";
}
