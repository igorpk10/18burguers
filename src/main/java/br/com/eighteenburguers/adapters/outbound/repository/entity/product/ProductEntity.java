package br.com.eighteenburguers.adapters.outbound.repository.entity.product;

import br.com.eighteenburguers.core.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;

@Data
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private String description;

    private File image;
}
