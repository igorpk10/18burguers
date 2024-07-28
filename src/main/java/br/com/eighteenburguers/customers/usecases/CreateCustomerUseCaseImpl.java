package br.com.eighteenburguers.customers.usecases;

import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentService;
import br.com.eighteenburguers.customers.services.SaveCustomerService;
import br.com.eighteenburguers.customers.exceptions.CustomerAlreadyExistsException;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final FindCustomerByDocumentService findCustomerByDocumentService;
    private final SaveCustomerService saveCustomerService;

    public CreateCustomerUseCaseImpl(FindCustomerByDocumentService findCustomerByDocumentService,
                                     SaveCustomerService saveCustomerService) {
        this.findCustomerByDocumentService = findCustomerByDocumentService;
        this.saveCustomerService = saveCustomerService;
    }

    @Override
    public Customer execute(Customer customer) throws BusinessException {
        var createdCustomer = findCustomerByDocumentService.findByDocumentNumber(customer.getDocument().getNumber());

        if (createdCustomer != null) {
            throw new CustomerAlreadyExistsException();
        }

        return saveCustomerService.save(customer);
    }

}
