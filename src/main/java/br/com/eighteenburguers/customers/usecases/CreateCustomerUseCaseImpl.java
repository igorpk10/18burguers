package br.com.eighteenburguers.customers.usecases;

import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentService;
import br.com.eighteenburguers.customers.services.SaveCustomerService;
import br.com.eighteenburguers.customers.exceptions.CustomerAlreadyExistsException;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final FindCustomerByDocumentService findCustomerAdapterPort;
    private final SaveCustomerService saveCustomerAdapterPort;

    public CreateCustomerUseCaseImpl(FindCustomerByDocumentService findCustomerAdapterPort,
                                     SaveCustomerService saveCustomerAdapterPort) {
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
