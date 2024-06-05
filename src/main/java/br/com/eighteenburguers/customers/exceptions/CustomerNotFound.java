package br.com.eighteenburguers.customers.exceptions;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public class CustomerNotFound extends BusinessException {
    
    public CustomerNotFound (){
        super("CNT", "Usuário não encontrado");
    }


}
