package br.com.eighteenburguers.core.usecase.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.FindCustomerUseCasePort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindByFederalIdCustomerAdapterPort;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerNotFound;

@Component
public class FindCustomerUseCase implements FindCustomerUseCasePort {

    @Autowired
    private FindByFederalIdCustomerAdapterPort findByFederalIdCustomerAdapterPort;

    @Override
    public Customer execute(String id) throws BusinessException {
        var customer = findByFederalIdCustomerAdapterPort.findByDocumentNumber(id);

        if(customer == null){
            throw new CustomerNotFound();
        }


        return findByFederalIdCustomerAdapterPort.findByDocumentNumber(id);
    }

}
