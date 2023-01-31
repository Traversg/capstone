package com.nashss.se.fivelifts.dynamodb.models;

import com.nashss.se.fivelifts.converters.DateConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.Date;
import java.util.List;

/**
 * Represents a Workout in the workout table.
 */
@DynamoDBTable(tableName = "workouts")
public class Workout {
    private String email;
    private Date date;
    private String workoutType;
    private Date timeStarted;
    private Date timeEnded;
    private int squatWeight;
    private int benchWeight;
    private int ohpWeight;
    private int rowWeight;
    private int deadliftWeight;
    private List<Integer> squatReps;
    private List<Integer> benchReps;
    private List<Integer> ohpReps;
    private List<Integer> rowReps;
    private List<Integer> deadliftReps;
    private boolean isComplete;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @DynamoDBAttribute(attributeName = "squatWeight")
    public int getSquatWeight() {
        return squatWeight;
    }

    public void setSquatWeight(int squatWeight) {
        this.squatWeight = squatWeight;
    }

    @DynamoDBAttribute(attributeName = "benchWeight")
    public int getBenchWeight() {
        return benchWeight;
    }

    public void setBenchWeight(int benchWeight) {
        this.benchWeight = benchWeight;
    }

    @DynamoDBAttribute(attributeName = "ohpWeight")
    public int getOhpWeight() {
        return ohpWeight;
    }

    public void setOhpWeight(int ohpWeight) {
        this.ohpWeight = ohpWeight;
    }

    @DynamoDBAttribute(attributeName = "rowWeight")
    public int getRowWeight() {
        return rowWeight;
    }

    public void setRowWeight(int rowWeight) {
        this.rowWeight = rowWeight;
    }

    @DynamoDBAttribute(attributeName = "deadLiftWeight")
    public int getDeadliftWeight() {
        return deadliftWeight;
    }

    public void setDeadliftWeight(int deadliftWeight) {
        this.deadliftWeight = deadliftWeight;
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
