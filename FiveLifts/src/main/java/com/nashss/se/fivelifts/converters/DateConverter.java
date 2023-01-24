package com.nashss.se.fivelifts.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateConverter implements DynamoDBTypeConverter<String, Date> {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(Date date) {
        return GSON.toJson(date);
    }

    @Override
    public Date unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<Date>() { } .getType());
    }
}
