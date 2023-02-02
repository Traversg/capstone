package com.nashss.se.fivelifts.dynamodb;

import com.nashss.se.fivelifts.dynamodb.models.Workout;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a user using {@link Workout} to represent the model in DynamoDB.
 */
@Singleton
public class WorkoutDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a WorkoutDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the workouts table
     */

    @Inject
    public WorkoutDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a Workout by email.
     *
     * @param email The email to look up
     * @return The corresponding User if found
     */
    public Workout getLatestCompletedWorkout(String email) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":email", new AttributeValue().withS(email));
        valueMap.put(":workoutDate", new AttributeValue().withS(LocalDate.now().plusDays(1).toString()));
        DynamoDBQueryExpression<Workout> queryExpression = new DynamoDBQueryExpression<Workout>()
                .withKeyConditionExpression("email = :email and workoutDate < :workoutDate")
                .withExpressionAttributeValues(valueMap)
                .withScanIndexForward(false)
                .withLimit(1);
        PaginatedQueryList<Workout> latestWorkout = dynamoDbMapper.query(Workout.class, queryExpression);
        return latestWorkout.get(0);
    }

    /**
     * Saves (creates or updates) the given user.
     *
     * @param workout The user to save
     */
    public void saveWorkout(Workout workout) {
        dynamoDbMapper.save(workout);
    }
}