package br.com.eighteenburguers.core.usecase.customer.exceptions;

import br.com.eighteenburguers.core.exceptions.BusinessException;

public class CustomerNotFound extends BusinessException {
    
    public CustomerNotFound (){
        super("CNT", "Usuário não encontrado");
    }


}
