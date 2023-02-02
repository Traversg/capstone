package com.nashss.se.fivelifts.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a User in the user table.
 */
@DynamoDBTable(tableName = "users")
public class User {
    private String email;
    private String name;
    private double bodyWeight;
    private int deadlift;
    private int squat;
    private int benchPress;
    private int overheadPress;
    private int barbellRow;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "bodyWeight")
    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    @DynamoDBAttribute(attributeName = "deadlift")
    public int getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(int deadlift) {
        this.deadlift = deadlift;
    }
    @DynamoDBAttribute(attributeName = "squat")
    public int getSquat() {
        return squat;
    }

    public void setSquat(int squat) {
        this.squat = squat;
    }

    @DynamoDBAttribute(attributeName = "benchPress")
    public int getBenchPress() {
        return benchPress;
    }

    public void setBenchPress(int benchPress) {
        this.benchPress = benchPress;
    }
    @DynamoDBAttribute(attributeName = "overheadPress")
    public int getOverheadPress() {
        return overheadPress;
    }

    public void setOverheadPress(int overheadPress) {
        this.overheadPress = overheadPress;
    }
    @DynamoDBAttribute(attributeName = "barbellRow")
    public int getBarbellRow() {
        return barbellRow;
    }

    public void setBarbellRow(int barbellRow) {
        this.barbellRow = barbellRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }
}
