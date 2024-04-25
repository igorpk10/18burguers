package com.fiap.techchallenge.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "customers")
public class Customer {
    @Id
    private int id;
    private String cpf;
    private String name;
    private String email;
}
