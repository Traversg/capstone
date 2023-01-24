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
    private String id;
    private String userName;
    private double bodyWeight;
    private int deadlift;
    private int squat;
    private int bench;
    private int overheadPress;
    private int barbellRow;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setUserId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute(attributeName = "bodyWeight")
    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setWeight(double bodyWeight) {
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

    @DynamoDBAttribute(attributeName = "bench")
    public int getBench() {
        return bench;
    }

    public void setBench(int bench) {
        this.bench = bench;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
