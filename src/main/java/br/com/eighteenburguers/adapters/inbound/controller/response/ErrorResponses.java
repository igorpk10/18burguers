package br.com.eighteenburguers.adapters.inbound.controller.response;

import java.util.HashSet;
import java.util.Set;

import br.com.eighteenburguers.core.exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponses {
    
    private Set<ErrorResponse> errors;

    public ErrorResponses() {
        this.errors = new HashSet<>();
    }

    public ErrorResponses(ErrorResponse error) {
        this.errors = Set.of(error);
    }

    public ErrorResponses(ErrorResponse ...errors) {
        this.errors = Set.of(errors);
    }

    public ErrorResponses(BusinessException ex) {
        this.errors = Set.of(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }

    public void add(ErrorResponse errorResponse) {
        this.errors.add(errorResponse);
    }

}
