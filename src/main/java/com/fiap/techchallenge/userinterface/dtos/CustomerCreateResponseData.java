package com.fiap.techchallenge.userinterface.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateResponseData {
    private int id;
    private String message;
}
