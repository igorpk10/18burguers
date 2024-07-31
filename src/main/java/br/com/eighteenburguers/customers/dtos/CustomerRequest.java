package br.com.eighteenburguers.customers.dtos;

import org.hibernate.validator.constraints.br.CPF;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequest {
    
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 55)
    private String name;

    @CPF
    @NotEmpty
    @NonNull
    private String cpf;

    @Email
    @NotEmpty
    @NonNull
    private String email;
}
