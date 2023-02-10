package com.nashss.se.fivelifts.unittests.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WorkoutDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private WorkoutDao workoutDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workoutDao = new WorkoutDao(dynamoDBMapper);
    }

    @Test
    public void saveWorkout_callsMapperWithWorkout() {
        // GIVEN
        Workout workout = new Workout();

        // WHEN
        workoutDao.saveWorkout(workout);

        // THEN
        verify(dynamoDBMapper).save(workout);
    }
}
