package br.com.eighteenburguers.category.configs;

import br.com.eighteenburguers.category.model.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryAttributeConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category attribute) {
        return attribute.getCodigo();
    }

    @Override
    public Category convertToEntityAttribute(Integer dbData) {
        return Category.ofCodigo(dbData);
    }
    
}
