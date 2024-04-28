package br.com.eighteenburguers.adapters.outbound.repository.customer;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.domain.Document;
import br.com.eighteenburguers.core.domain.DocumentType;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS, injectionStrategy = CONSTRUCTOR)
public interface CustomerEntityMapper {

    @Mapping(target = "documentNumber", source = "document.number")
    @Mapping(target = "documentType", source = "document.type")
    CustomerEntity toEntity(Customer customer);

    @Mapping(target = "document", source = "documentNumber", qualifiedByName = "documentMap")
    Customer toCustomer(CustomerEntity entity);

    @Named("documentMap")
    default Document documentMap(String documentNumber) {
        return new Document(DocumentType.CPF, documentNumber);
    }

}
