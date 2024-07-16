package br.com.eighteenburguers.customers.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.eighteenburguers.customers.dtos.CustomerResponse;
import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.customers.entitys.Document;
import br.com.eighteenburguers.customers.entitys.DocumentType;
import br.com.eighteenburguers.customers.dtos.CustomerRequest;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS, injectionStrategy = CONSTRUCTOR)
public interface CustomerMapper {
    
    @Mapping(target = "document", source = "cpf", qualifiedByName = "documentMap")
    Customer toCustomer(CustomerRequest request);

    @Mapping(target = "cpf", source = "document.number")
    CustomerResponse toResponse(Customer customer);

    @Named("documentMap")
    default Document documentMap(String documentNumber) {
        if(documentNumber == null) return null;
        return new Document(DocumentType.CPF, documentNumber);
    }
}
