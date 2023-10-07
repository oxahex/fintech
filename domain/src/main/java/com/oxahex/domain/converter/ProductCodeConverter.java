package com.oxahex.domain.converter;

import com.oxahex.domain.type.ProductCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductCodeConverter
        implements AttributeConverter<ProductCode, String>
{
    @Override
    public String convertToDatabaseColumn(ProductCode attribute) {
        // DB -> ENUM
        return attribute.getProductCode();
    }

    @Override
    public ProductCode convertToEntityAttribute(String dbData) {
        // ENUM -> DB
        return ProductCode.getCode(dbData);
    }
}
