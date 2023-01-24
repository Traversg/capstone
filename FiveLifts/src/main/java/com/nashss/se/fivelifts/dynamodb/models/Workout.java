package com.nashss.se.fivelifts.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.fivelifts.converters.DateConverter;

import java.util.Date;
import java.util.List;

/**
 * Represents a Workout in the workout table.
 */
@DynamoDBTable(tableName = "workouts")
public class Workout {
    public static final String WORKOUT_IN_PROGRESS_INDEX = "WorkoutInProgressIndex";
    private String userId;
    private Date date;
    private String workoutType;
    private Date timeStarted;
    private Date timeEnded;
    private List<Integer> squatReps;
    private List<Integer> benchReps;
    private List<Integer> ohpReps;
    private List<Integer> rowReps;
    private List<Integer> deadliftReps;
    private boolean isComplete;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = WORKOUT_IN_PROGRESS_INDEX, attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBTypeConverted(converter = DateConverter.class)
    @DynamoDBRangeKey(attributeName = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "workoutType")
    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    @DynamoDBTypeConverted(converter = DateConverter.class)
    @DynamoDBAttribute(attributeName = "timeStarted")
    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    @DynamoDBTypeConverted(converter = DateConverter.class)
    @DynamoDBAttribute(attributeName = "timeEnded")
    public Date getTimeEnded() {
        return timeEnded;
    }

    public void setTimeEnded(Date timeEnded) {
        this.timeEnded = timeEnded;
    }

    @DynamoDBAttribute(attributeName = "squatReps")
    public List<Integer> getSquatReps() {
        return squatReps;
    }

    public void setSquatReps(List<Integer> squatReps) {
        this.squatReps = squatReps;
    }

    @DynamoDBAttribute(attributeName = "benchReps")
    public List<Integer> getBenchReps() {
        return benchReps;
    }

    public void setBenchReps(List<Integer> benchReps) {
        this.benchReps = benchReps;
    }

    @DynamoDBAttribute(attributeName = "ohpReps")
    public List<Integer> getOhpReps() {
        return ohpReps;
    }

    public void setOhpReps(List<Integer> ohpReps) {
        this.ohpReps = ohpReps;
    }

    @DynamoDBAttribute(attributeName = "rowReps")
    public List<Integer> getRowReps() {
        return rowReps;
    }

    public void setRowReps(List<Integer> rowReps) {
        this.rowReps = rowReps;
    }

    @DynamoDBAttribute(attributeName = "deadliftReps")
    public List<Integer> getDeadliftReps() {
        return deadliftReps;
    }

    public void setDeadliftReps(List<Integer> deadliftReps) {
        this.deadliftReps = deadliftReps;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = WORKOUT_IN_PROGRESS_INDEX, attributeName = "isComplete")
    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
