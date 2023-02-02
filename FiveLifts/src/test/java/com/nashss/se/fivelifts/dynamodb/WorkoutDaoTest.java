package com.nashss.se.fivelifts.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutDaoTest {
    private static final String EMAIL = "TEST@EMAIL.COM";
    private static LocalDate oldDate;
    private static LocalDate mostRecentDate;
    private DynamoDBMapper dynamoDBMapper;

    private WorkoutDao workoutDao;

    @BeforeEach
    public void setUp() {
        dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
        workoutDao = new WorkoutDao(dynamoDBMapper);

        oldDate = LocalDate.of(2022, Month.DECEMBER, 30);
        mostRecentDate = LocalDate.of(2023, Month.JANUARY, 1);


        // make sure the EMAILs we don't expect in db aren't there before tests begin
        // (after getting a DynamoDBMapper)
        deleteTestData();
    }

    @Test
    void getLatestCompletedWorkout_withEmailAndQueryExpression_returnsMostRecentWorkout() {
        // GIVEN
        Workout oldest = new Workout();
        oldest.setEmail(EMAIL);
        oldest.setWorkoutDate(oldDate);
        workoutDao.saveWorkout(oldest);

        Workout mostRecent = new Workout();
        mostRecent.setEmail(EMAIL);
        mostRecent.setWorkoutDate(mostRecentDate);
        workoutDao.saveWorkout(mostRecent);

        // WHEN
        Workout result = workoutDao.getLatestCompletedWorkout(EMAIL);

        // THEN
        assertEquals(mostRecent.getWorkoutDate(), result.getWorkoutDate());
    }

    private void deleteTestData() {
        Workout workout = new Workout();
        workout.setEmail(EMAIL);
        workout.setWorkoutDate(oldDate);
        dynamoDBMapper.delete(workout);
        workout.setWorkoutDate(mostRecentDate);
        dynamoDBMapper.delete(workout);
    }
}
