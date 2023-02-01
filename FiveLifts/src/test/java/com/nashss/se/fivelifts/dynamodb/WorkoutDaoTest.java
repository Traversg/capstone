package com.nashss.se.fivelifts.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutDaoTest {
    private static final String EMAIL = "TEST@EMAIL.COM";
    private static Calendar oldDate;
    private static Calendar mostRecentDate;
    private DynamoDBMapper dynamoDBMapper;

    private WorkoutDao workoutDao;

    @BeforeEach
    public void setUp() {
        dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
        workoutDao = new WorkoutDao(dynamoDBMapper);

        oldDate = Calendar.getInstance();
        oldDate.set(2022, Calendar.DECEMBER, 30);

        mostRecentDate = Calendar.getInstance();
        mostRecentDate.set(2023, Calendar.JANUARY, 1);

        // make sure the ASINs we don't expect in db aren't there before tests begin
        // (after getting a DynamoDBMapper)
        deleteTestData();
    }

    @Test
    void getLatestCompletedWorkout_withEmailAndQueryExpression_returnsMostRecentWorkout() {
        // GIVEN
        Workout oldest = new Workout();
        oldest.setEmail(EMAIL);
        oldest.setIsComplete(true);

        oldest.setDate(oldDate);

        Workout mostRecent = new Workout();
        mostRecent.setEmail(EMAIL);
        mostRecent.setIsComplete(true);
        Calendar mostRecentDate = Calendar.getInstance();
        mostRecent.setDate(mostRecentDate);

        workoutDao.saveWorkout(oldest);
        workoutDao.saveWorkout(mostRecent);

        // WHEN
        Workout result = workoutDao.getLatestCompletedWorkout(EMAIL);

        // THEN
        assertEquals(mostRecent.getDate(), result.getDate());
    }

    private void deleteTestData() {
        Workout workout = new Workout();
        workout.setEmail(EMAIL);
        workout.setDate(oldDate);
        dynamoDBMapper.delete(workout);
        workout.setDate(mostRecentDate);
        dynamoDBMapper.delete(mostRecentDate);
    }
}
