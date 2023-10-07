package com.oxahex.domain.converter;

import com.oxahex.domain.type.OrganizationCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrganizationCodeConverter
        implements AttributeConverter<OrganizationCode, String>
{
    @Override
    public String convertToDatabaseColumn(OrganizationCode attribute) {
        return attribute.getOrganizationCode();
    }

    @Override
    public OrganizationCode convertToEntityAttribute(String dbData) {
        return OrganizationCode.getCode(dbData);
    }
}
