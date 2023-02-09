package com.nashss.se.fivelifts.integrationtests.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fivelifts.dynamodb.DynamoDbClientProvider;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
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
    void getMostRecentWorkout_withEmailWithWorkouts_returnsMostRecentWorkout() {
        // GIVEN
        Workout oldest = new Workout();
        oldest.setEmail(EMAIL);
        oldest.setWorkoutDate(oldDate);

        Workout mostRecent = new Workout();
        mostRecent.setEmail(EMAIL);
        mostRecent.setWorkoutDate(mostRecentDate);

        workoutDao.saveWorkout(mostRecent);
        workoutDao.saveWorkout(oldest);

        // WHEN
        Optional<Workout> result = workoutDao.getMostRecentWorkout(EMAIL);

        // THEN
        assertEquals(mostRecent.getWorkoutDate(), result.get().getWorkoutDate());
    }

    @Test
    void getMostRecentWorkout_withEmailWithoutWorkouts_returnsEmptyOptional() {
        // GIVEN
        deleteTestData();

        // WHEN
        Optional<Workout> result = workoutDao.getMostRecentWorkout(EMAIL);

        // THEN
        assertSame(Optional.empty(), result);
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
