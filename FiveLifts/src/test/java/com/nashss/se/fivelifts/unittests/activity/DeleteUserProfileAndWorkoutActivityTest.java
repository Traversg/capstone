package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.DeleteUserProfileAndWorkoutHistoryActivity;
import com.nashss.se.fivelifts.activity.requests.DeleteUserProfileAndWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.DeleteUserProfileAndWorkoutHistoryResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteUserProfileAndWorkoutActivityTest {
    @Mock
    private UserDao userDao;
    @Mock
    private WorkoutDao workoutDao;

    private DeleteUserProfileAndWorkoutHistoryActivity deleteUserProfileAndWorkoutHistoryActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        deleteUserProfileAndWorkoutHistoryActivity =
                new DeleteUserProfileAndWorkoutHistoryActivity(userDao, workoutDao);
    }

    @Test
    public void handleRequest_withEmail_returnsTrueResult() {
        // GIVEN
        String userEmail = "true@email.com";
        LocalDate localDate = LocalDate.of(2023, Month.JANUARY, 1);

        User user = new User();
        user.setEmail(userEmail);

        Workout workout = new Workout();
        workout.setEmail(userEmail);
        workout.setWorkoutDate(localDate);

        List<Workout> workoutHistory = List.of(workout);

        DeleteUserProfileAndWorkoutHistoryRequest request = DeleteUserProfileAndWorkoutHistoryRequest.builder()
                .withEmail(userEmail)
                .build();

        when(workoutDao.getWorkoutHistory(userEmail)).thenReturn(workoutHistory);

        // WHEN
        DeleteUserProfileAndWorkoutHistoryResult result =
                deleteUserProfileAndWorkoutHistoryActivity.handleRequest(request);

        // THEN
        verify(workoutDao, times(workoutHistory.size())).deleteWorkout(workout);
        verify(userDao).deleteUser(user);
        assertEquals(userEmail, result.getEmail());
        assertTrue(result.isDeleted());
    }
}
