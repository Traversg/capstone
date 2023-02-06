package com.nashss.se.fivelifts.converters;

import com.nashss.se.fivelifts.enums.WorkoutType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

/**
 * Converts DynamoDB String Type into Data Type.
 */
public class WorkoutTypeConverter implements DynamoDBTypeConverter<String, WorkoutType> {

    @Override
    public String convert(WorkoutType workoutType) {
        return workoutType.name();
    }

    @Override
    public WorkoutType unconvert(String workoutType) {
        return WorkoutType.valueOf(workoutType);
    }
}
