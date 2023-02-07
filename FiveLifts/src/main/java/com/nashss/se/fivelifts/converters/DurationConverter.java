package com.nashss.se.fivelifts.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Duration;

/**
 * Converts DynamoDB String Type into Data Type.
 */
public class DurationConverter implements DynamoDBTypeConverter<String, Duration> {
    @Override
    public String convert(Duration duration) {
        return duration.toString();
    }

    @Override
    public Duration unconvert(String dynamoDbRepresentation) {
        return Duration.parse(dynamoDbRepresentation);
    }
}
