package com.nashss.se.fivelifts.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.sql.Timestamp;

/**
 * Converts DynamoDB String Type into Data Type.
 */
public class TimestampConverter implements DynamoDBTypeConverter<String, Timestamp> {
    @Override
    public String convert(Timestamp timestamp) {
        return timestamp.toString();
    }

    @Override
    public Timestamp unconvert(String dynamoDbRepresentation) {
        return Timestamp.valueOf(dynamoDbRepresentation);
    }
}
