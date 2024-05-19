package br.com.eighteenburguers.core.usecase.customer;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.CreateCustomerInputPort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerByFederalIdOutputPort;
import br.com.eighteenburguers.core.ports.outbound.customer.SaveCustomerOutputPort;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerAlreadyExistsException;

public class CreateCustomerUseCase implements CreateCustomerInputPort {

    private final FindCustomerByFederalIdOutputPort findCustomerAdapterPort;
    private final SaveCustomerOutputPort saveCustomerAdapterPort;

    public CreateCustomerUseCase(FindCustomerByFederalIdOutputPort findCustomerAdapterPort,
            SaveCustomerOutputPort saveCustomerAdapterPort) {
            this.findCustomerAdapterPort = findCustomerAdapterPort;
            this.saveCustomerAdapterPort = saveCustomerAdapterPort; 
    }

    @Override
    public Customer execute(Customer customer) throws BusinessException {
        var createdCustomer = findCustomerAdapterPort.findByDocumentNumber(customer.getDocument().getNumber());

        if (createdCustomer != null) {
            throw new CustomerAlreadyExistsException();
        }

        return saveCustomerAdapterPort.save(customer);
    }

}
