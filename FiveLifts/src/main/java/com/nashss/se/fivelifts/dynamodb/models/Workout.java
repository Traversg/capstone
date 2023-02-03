package com.nashss.se.fivelifts.dynamodb.models;

import com.nashss.se.fivelifts.converters.DateConverter;
import com.nashss.se.fivelifts.converters.LocalDateConverter;
import com.nashss.se.fivelifts.converters.WorkoutTypeConverter;
import com.nashss.se.fivelifts.enums.WorkoutType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Represents a Workout in the workout table.
 */
@DynamoDBTable(tableName = "workouts")
public class Workout {
    private String email;
    private LocalDate workoutDate;
    private WorkoutType workoutType;
    private Date timeStarted;
    private Date timeEnded;
    private int squatWeight;
    private int benchPressWeight;
    private int overheadPressWeight;
    private int barbellRowWeight;
    private int deadliftWeight;
    private List<Integer> squatReps;
    private List<Integer> benchPressReps;
    private List<Integer> overheadPressReps;
    private List<Integer> barbellRowReps;
    private List<Integer> deadliftReps;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    @DynamoDBRangeKey(attributeName = "workoutDate")
    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    @DynamoDBTypeConverted(converter = WorkoutTypeConverter.class)
    @DynamoDBAttribute(attributeName = "workoutType")
    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
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

    @DynamoDBAttribute(attributeName = "benchPressWeight")
    public int getBenchPressWeight() {
        return benchPressWeight;
    }

    public void setBenchPressWeight(int benchPressWeight) {
        this.benchPressWeight = benchPressWeight;
    }

    @DynamoDBAttribute(attributeName = "overheadPressWeight")
    public int getOverheadPressWeight() {
        return overheadPressWeight;
    }

    public void setOverheadPressWeight(int overheadPressWeight) {
        this.overheadPressWeight = overheadPressWeight;
    }

    @DynamoDBAttribute(attributeName = "barbellRowWeight")
    public int getBarbellRowWeight() {
        return barbellRowWeight;
    }

    public void setBarbellRowWeight(int barbellRowWeight) {
        this.barbellRowWeight = barbellRowWeight;
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

    @DynamoDBAttribute(attributeName = "benchPressReps")
    public List<Integer> getBenchPressReps() {
        return benchPressReps;
    }

    public void setBenchPressReps(List<Integer> benchPressReps) {
        this.benchPressReps = benchPressReps;
    }

    @DynamoDBAttribute(attributeName = "overheadPressReps")
    public List<Integer> getOverheadPressReps() {
        return overheadPressReps;
    }

    public void setOverheadPressReps(List<Integer> overheadPressReps) {
        this.overheadPressReps = overheadPressReps;
    }

    @DynamoDBAttribute(attributeName = "barbellRowReps")
    public List<Integer> getBarbellRowReps() {
        return barbellRowReps;
    }

    public void setBarbellRowReps(List<Integer> barbellRowReps) {
        this.barbellRowReps = barbellRowReps;
    }

    @DynamoDBAttribute(attributeName = "deadliftReps")
    public List<Integer> getDeadliftReps() {
        return deadliftReps;
    }

    public void setDeadliftReps(List<Integer> deadliftReps) {
        this.deadliftReps = deadliftReps;
    }
}
