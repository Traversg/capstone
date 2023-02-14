package com.nashss.se.fivelifts.dynamodb;

import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.exceptions.WorkoutNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
     * Retrieves the most recent Workout by email.
     *
     * @param email The email to look up
     * @return The corresponding Workout if found
     */
    public Optional<Workout> getMostRecentWorkout(String email) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":email", new AttributeValue().withS(email));
        DynamoDBQueryExpression<Workout> queryExpression = new DynamoDBQueryExpression<Workout>()
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(valueMap)
                .withScanIndexForward(false)
                .withLimit(1);
        PaginatedQueryList<Workout> latestWorkout = dynamoDbMapper.query(Workout.class, queryExpression);

        if (latestWorkout.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(latestWorkout.get(0));
    }

    /**
     * Retrieves the workout history by email.
     *
     * @param email The email to look up
     * @return The corresponding Workout list if found
     */
    public List<Workout> getWorkoutHistory(String email) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":email", new AttributeValue().withS(email));
        DynamoDBQueryExpression<Workout> queryExpression = new DynamoDBQueryExpression<Workout>()
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(valueMap)
                .withScanIndexForward(false);
        PaginatedQueryList<Workout> workoutHistory = dynamoDbMapper.query(Workout.class, queryExpression);

        if (workoutHistory.isEmpty()) {
            throw new WorkoutNotFoundException(String.format(
                    "Could not find workouts associated with email '%s'", email
            ));
        }

        return workoutHistory;
    }

    /**
     * Saves (creates or updates) the given workout.
     *
     * @param workout The workout to save
     */
    public void saveWorkout(Workout workout) {
        dynamoDbMapper.save(workout);
    }

    /**
     * Deletes the given workout.
     *
     * @param workout The workout to delete
     */
    public void deleteWorkout(Workout workout) {
        dynamoDbMapper.delete(workout);
    }
}
