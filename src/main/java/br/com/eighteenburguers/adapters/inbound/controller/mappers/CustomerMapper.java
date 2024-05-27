package br.com.eighteenburguers.adapters.inbound.controller.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.eighteenburguers.adapters.inbound.controller.request.CustomerRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.CustomerResponse;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.domain.Document;
import br.com.eighteenburguers.core.domain.DocumentType;

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
