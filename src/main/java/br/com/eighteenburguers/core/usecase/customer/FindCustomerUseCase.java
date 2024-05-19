package br.com.eighteenburguers.core.usecase.customer;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.FindCustomerInputPort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerByFederalIdOutputPort;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerNotFound;

public class FindCustomerUseCase implements FindCustomerInputPort {

    private FindCustomerByFederalIdOutputPort findByFederalIdCustomerAdapterPort;

    public FindCustomerUseCase(FindCustomerByFederalIdOutputPort findByFederalIdCustomerAdapterPort){
        this.findByFederalIdCustomerAdapterPort = findByFederalIdCustomerAdapterPort;
    }

    @Override
    public Customer execute(String id) throws BusinessException {
        var customer = findByFederalIdCustomerAdapterPort.findByDocumentNumber(id);

        if(customer == null){
            throw new CustomerNotFound();
        }

        return findByFederalIdCustomerAdapterPort.findByDocumentNumber(id);
    }
}