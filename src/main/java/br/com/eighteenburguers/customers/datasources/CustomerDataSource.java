package br.com.eighteenburguers.customers.datasources;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.customers.entitys.CustomerEntity;

@Repository
public interface CustomerDataSource extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);
}
