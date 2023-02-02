package com.nashss.se.fivelifts.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;

/**
 * Converts DynamoDB String Type into Data Type.
 */
public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(LocalDate date) {
        return date.toString();
    }

    @Override
    public LocalDate unconvert(String dynamoDbRepresentation) {
        return LocalDate.parse(dynamoDbRepresentation);
    }
}
