package com.nashss.se.fivelifts.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class CalendarConverter implements DynamoDBTypeConverter<String, Calendar> {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(Calendar calendar) {
        return GSON.toJson(calendar);
    }

    @Override
    public Calendar unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<Calendar>() { } .getType());
    }
}
