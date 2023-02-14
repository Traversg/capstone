package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.GetMostRecentWorkoutActivity;
import com.nashss.se.fivelifts.activity.requests.GetMostRecentWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.GetMostRecentWorkoutResult;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.exceptions.WorkoutNotFoundException;
import com.nashss.se.fivelifts.metrics.MetricsConstants;
import com.nashss.se.fivelifts.metrics.MetricsPublisher;
import com.nashss.se.fivelifts.models.WorkoutModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetMostRecentWorkoutActivityTest {
    @Mock
    private WorkoutDao workoutDao;
    @Mock
    private MetricsPublisher metricsPublisher;

    private GetMostRecentWorkoutActivity getMostRecentWorkoutActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getMostRecentWorkoutActivity = new GetMostRecentWorkoutActivity(workoutDao, metricsPublisher);
    }

    @Test
    public void handleRequest_withCurrentUserEmail_returnsMostRecentWorkout() {
        // GIVEN
        String expectedUserEmail = "test@email.com";
        LocalDate expectedDate = LocalDate.of(2023, Month.JANUARY, 1);
        WorkoutType expectedWorkoutType = WorkoutType.WORKOUT_A;
        int expectedSquatWeight = 225;
        int expectedBenchPressWeight = 175;
        int expectedBarbellRowWeight = 150;
        List<Integer> expectedSquatReps = List.of(5,5,5,5,4);
        List<Integer> expectedBenchPressReps = List.of(5,5,4,5,4);
        List<Integer> expectedBarbellRowReps = List.of(5,5,5,5,5);

        Workout mostRecentWorkout = new Workout();
        mostRecentWorkout.setEmail(expectedUserEmail);
        mostRecentWorkout.setWorkoutDate(expectedDate);
        mostRecentWorkout.setWorkoutType(expectedWorkoutType);
        mostRecentWorkout.setSquatWeight(expectedSquatWeight);
        mostRecentWorkout.setBenchPressWeight(expectedBenchPressWeight);
        mostRecentWorkout.setBarbellRowWeight(expectedBarbellRowWeight);
        mostRecentWorkout.setSquatReps(expectedSquatReps);
        mostRecentWorkout.setBenchPressReps(expectedBenchPressReps);
        mostRecentWorkout.setBarbellRowReps(expectedBarbellRowReps);
        when(workoutDao.getMostRecentWorkout(expectedUserEmail)).thenReturn(Optional.of(mostRecentWorkout));

        GetMostRecentWorkoutRequest request = GetMostRecentWorkoutRequest.builder()
                .withEmail(expectedUserEmail)
                .build();

        // WHEN
        GetMostRecentWorkoutResult result = getMostRecentWorkoutActivity.handleRequest(request);
        WorkoutModel mostRecentWorkoutModelResult = result.getMostRecentWorkout();

        // THEN
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETMOSTRECENTWORKOUT_WORKOUTNOTFOUND_COUNT), anyDouble());
        assertEquals(expectedUserEmail, mostRecentWorkoutModelResult.getEmail());
        assertEquals(expectedWorkoutType, mostRecentWorkoutModelResult.getWorkoutType());
        assertEquals(expectedDate, mostRecentWorkoutModelResult.getWorkoutDate());
        assertEquals(expectedSquatWeight, mostRecentWorkoutModelResult.getSquatWeight());
        assertEquals(expectedBenchPressWeight, mostRecentWorkoutModelResult.getBenchPressWeight());
        assertEquals(expectedBarbellRowWeight, mostRecentWorkoutModelResult.getBarbellRowWeight());
        assertEquals(expectedSquatReps, mostRecentWorkoutModelResult.getSquatReps());
        assertEquals(expectedBenchPressReps, mostRecentWorkoutModelResult.getBenchPressReps());
        assertEquals(expectedBarbellRowReps, mostRecentWorkoutModelResult.getBarbellRowReps());
    }

    @Test
    public void handleRequest_withUserEmailWithNoWorkoutHistory_throwsWorkoutNotFoundException() {
        // GIVEN
        String emailWithoutWorkout = "noWorkout@email.com";
        when(workoutDao.getMostRecentWorkout(emailWithoutWorkout)).thenReturn(Optional.empty());

        GetMostRecentWorkoutRequest request = GetMostRecentWorkoutRequest.builder()
                .withEmail(emailWithoutWorkout)
                .build();

        // WHEN + THEN
        assertThrows(WorkoutNotFoundException.class, () -> getMostRecentWorkoutActivity.handleRequest(request));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETMOSTRECENTWORKOUT_WORKOUTNOTFOUND_COUNT), anyDouble());
    }
}
