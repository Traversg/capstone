package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.AddWorkoutActivity;
import com.nashss.se.fivelifts.activity.requests.AddWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.AddWorkoutResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.exceptions.BodyWeightLessThanZeroException;
import com.nashss.se.fivelifts.exceptions.RepsLessThanZeroException;
import com.nashss.se.fivelifts.exceptions.TooManyRepsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddWorkoutActivityTest {
    @Mock
    private WorkoutDao workoutDao;

    @Mock
    private UserDao userDao;

    private AddWorkoutActivity addWorkoutActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        addWorkoutActivity = new AddWorkoutActivity(workoutDao, userDao);
    }

    @Test
    public void handleRequest_withCompletedWorkoutA_createsAndSavesWorkout() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        String requestWorkoutType = "Workout A";
        WorkoutType expectedWorkoutType = WorkoutType.WORKOUT_A;
        String requestWorkoutDate = "2023-02-07";
        LocalDate expectedWorkoutDate = LocalDate.parse(requestWorkoutDate);
        String requestTimeStarted = "2023-02-07T10:45:26.798462";
        String requestTimeEnded = "2023-02-07T11:45:26.798462";
        Duration expectedTotalWorkoutTime = Duration.between(LocalDateTime.parse(requestTimeStarted),
                LocalDateTime.parse(requestTimeEnded));
        double expectedBodyWeight = 175.0;
        int expectedDeadlift = 300;
        int expectedSquat = 250;
        int expectedBenchPress = 200;
        int expectedBarbellRow = 175;
        int expectedOverheadPress = 125;
        List<Integer> expectedSquatReps = List.of(5,5,5,5,5);
        List<Integer> expectedBenchPressReps = List.of(5,5,5,5,5);
        List<Integer> expectedBarbellRowReps = List.of(5,5,5,5,5);
        List<Integer> expectedDeadliftReps = new ArrayList<>();
        List<Integer> expectedOverheadPressReps = new ArrayList<>();

        int expectedIncrementedSquat = 255;
        int expectedIncrementedBenchPress = 205;
        int expectedIncrementedBarbellRow = 180;

        User user = new User();
        user.setEmail(expectedEmail);
        user.setSquat(250);
        user.setBenchPress(200);
        user.setBarbellRow(175);

        when(userDao.getUser(expectedEmail)).thenReturn(user);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withWorkoutType(requestWorkoutType)
                .withWorkoutDate(requestWorkoutDate)
                .withTimeStarted(requestTimeStarted)
                .withTimeEnded(requestTimeEnded)
                .withBodyWeight(expectedBodyWeight)
                .withDeadliftWeight(expectedDeadlift)
                .withSquatWeight(expectedSquat)
                .withBenchPressWeight(expectedBenchPress)
                .withBarbellRowWeight(expectedBarbellRow)
                .withOverheadPressWeight(expectedOverheadPress)
                .withSquatReps(expectedSquatReps)
                .withBenchPressReps(expectedBenchPressReps)
                .withBarbellRowReps(expectedBarbellRowReps)
                .withDeadliftReps(expectedDeadliftReps)
                .withOverheadPressReps(expectedOverheadPressReps)
                .build();

        // WHEN
        AddWorkoutResult result = addWorkoutActivity.handleRequest(request);

        // THEN
        verify(workoutDao).saveWorkout(any(Workout.class));
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedEmail, result.getWorkout().getEmail());
        assertEquals(expectedWorkoutType, result.getWorkout().getWorkoutType());
        assertEquals(expectedWorkoutDate, result.getWorkout().getWorkoutDate());
        assertEquals(expectedTotalWorkoutTime, result.getWorkout().getTotalWorkoutTime());
        assertEquals(expectedBodyWeight, result.getWorkout().getBodyWeight());
        assertEquals(expectedDeadlift, result.getWorkout().getDeadliftWeight());
        assertEquals(expectedSquat, result.getWorkout().getSquatWeight());
        assertEquals(expectedBenchPress, result.getWorkout().getBenchPressWeight());
        assertEquals(expectedBarbellRow, result.getWorkout().getBarbellRowWeight());
        assertEquals(expectedOverheadPress, result.getWorkout().getOverheadPressWeight());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedSquatReps, result.getWorkout().getSquatReps());
        assertEquals(expectedBenchPressReps, result.getWorkout().getBenchPressReps());
        assertEquals(expectedOverheadPressReps, result.getWorkout().getOverheadPressReps());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedBarbellRowReps, result.getWorkout().getBarbellRowReps());

        assertEquals(expectedIncrementedSquat, user.getSquat());
        assertEquals(expectedIncrementedBarbellRow, user.getBarbellRow());
        assertEquals(expectedIncrementedBenchPress, user.getBenchPress());
    }

    @Test
    public void handleRequest_withCompletedWorkoutB_createsAndSavesWorkout() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        String requestWorkoutType = "Workout A";
        WorkoutType expectedWorkoutType = WorkoutType.WORKOUT_A;
        String requestWorkoutDate = "2023-02-07";
        LocalDate expectedWorkoutDate = LocalDate.parse(requestWorkoutDate);
        String requestTimeStarted = "2023-02-07T10:45:26.798462";
        String requestTimeEnded = "2023-02-07T11:45:26.798462";
        Duration expectedTotalWorkoutTime = Duration.between(LocalDateTime.parse(requestTimeStarted),
                LocalDateTime.parse(requestTimeEnded));
        double expectedBodyWeight = 175.0;
        int expectedDeadlift = 300;
        int expectedSquat = 250;
        int expectedBenchPress = 200;
        int expectedBarbellRow = 175;
        int expectedOverheadPress = 125;
        List<Integer> expectedSquatReps = List.of(5,5,5,5,5);
        List<Integer> expectedBenchPressReps = new ArrayList<>();
        List<Integer> expectedBarbellRowReps = new ArrayList<>();
        List<Integer> expectedDeadliftReps = List.of(5);
        List<Integer> expectedOverheadPressReps = List.of(5,5,5,5,5);

        int expectedIncrementedSquat = 255;
        int expectedIncrementedOverheadPress = 130;
        int expectedIncrementedDeadlift = 310;

        User user = new User();
        user.setEmail(expectedEmail);
        user.setSquat(250);
        user.setOverheadPress(125);
        user.setDeadlift(300);

        when(userDao.getUser(expectedEmail)).thenReturn(user);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withWorkoutType(requestWorkoutType)
                .withWorkoutDate(requestWorkoutDate)
                .withTimeStarted(requestTimeStarted)
                .withTimeEnded(requestTimeEnded)
                .withBodyWeight(expectedBodyWeight)
                .withDeadliftWeight(expectedDeadlift)
                .withSquatWeight(expectedSquat)
                .withBenchPressWeight(expectedBenchPress)
                .withBarbellRowWeight(expectedBarbellRow)
                .withOverheadPressWeight(expectedOverheadPress)
                .withSquatReps(expectedSquatReps)
                .withBenchPressReps(expectedBenchPressReps)
                .withBarbellRowReps(expectedBarbellRowReps)
                .withDeadliftReps(expectedDeadliftReps)
                .withOverheadPressReps(expectedOverheadPressReps)
                .build();

        // WHEN
        AddWorkoutResult result = addWorkoutActivity.handleRequest(request);

        // THEN
        verify(workoutDao).saveWorkout(any(Workout.class));
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedEmail, result.getWorkout().getEmail());
        assertEquals(expectedWorkoutType, result.getWorkout().getWorkoutType());
        assertEquals(expectedWorkoutDate, result.getWorkout().getWorkoutDate());
        assertEquals(expectedTotalWorkoutTime, result.getWorkout().getTotalWorkoutTime());
        assertEquals(expectedBodyWeight, result.getWorkout().getBodyWeight());
        assertEquals(expectedDeadlift, result.getWorkout().getDeadliftWeight());
        assertEquals(expectedSquat, result.getWorkout().getSquatWeight());
        assertEquals(expectedBenchPress, result.getWorkout().getBenchPressWeight());
        assertEquals(expectedBarbellRow, result.getWorkout().getBarbellRowWeight());
        assertEquals(expectedOverheadPress, result.getWorkout().getOverheadPressWeight());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedSquatReps, result.getWorkout().getSquatReps());
        assertEquals(expectedBenchPressReps, result.getWorkout().getBenchPressReps());
        assertEquals(expectedOverheadPressReps, result.getWorkout().getOverheadPressReps());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedBarbellRowReps, result.getWorkout().getBarbellRowReps());

        assertEquals(expectedIncrementedSquat, user.getSquat());
        assertEquals(expectedIncrementedOverheadPress, user.getOverheadPress());
        assertEquals(expectedIncrementedDeadlift, user.getDeadlift());
    }

    @Test
    public void handleRequest_withRepMoreThanFive_throwsTooManyRepsException() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        List<Integer> squatReps = List.of(5,5,5,5,5);
        List<Integer> benchPressReps = new ArrayList<>();
        List<Integer> barbellRowReps = new ArrayList<>();
        List<Integer> deadliftReps = List.of(5);
        List<Integer> overheadPressWithSixReps = List.of(6,5,5,5,5);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withDeadliftWeight(300)
                .withSquatWeight(225)
                .withBenchPressWeight(175)
                .withBarbellRowWeight(150)
                .withOverheadPressWeight(125)
                .withSquatReps(squatReps)
                .withBenchPressReps(benchPressReps)
                .withBarbellRowReps(barbellRowReps)
                .withDeadliftReps(deadliftReps)
                .withOverheadPressReps(overheadPressWithSixReps)
                .build();

        // THEN
        assertThrows(TooManyRepsException.class, () -> addWorkoutActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_withRepLessThanZero_throwsLessThanZeroException() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        List<Integer> squatReps = List.of(5,5,5,5,5);
        List<Integer> benchPressReps = new ArrayList<>();
        List<Integer> barbellRowReps = new ArrayList<>();
        List<Integer> deadliftReps = List.of(5);
        List<Integer> overheadPressWithLessThanZeroReps = List.of(-1,5,5,5,5);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withDeadliftWeight(300)
                .withSquatWeight(225)
                .withBenchPressWeight(175)
                .withBarbellRowWeight(150)
                .withOverheadPressWeight(125)
                .withSquatReps(squatReps)
                .withBenchPressReps(benchPressReps)
                .withBarbellRowReps(barbellRowReps)
                .withDeadliftReps(deadliftReps)
                .withOverheadPressReps(overheadPressWithLessThanZeroReps)
                .build();

        // THEN
        assertThrows(RepsLessThanZeroException.class, () -> addWorkoutActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_withBodyWeightEqualsZero_returnsPreviousBodyWeight() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        String requestWorkoutType = "Workout A";
        WorkoutType expectedWorkoutType = WorkoutType.WORKOUT_A;
        String requestWorkoutDate = "2023-02-07";
        LocalDate expectedWorkoutDate = LocalDate.parse(requestWorkoutDate);
        String requestTimeStarted = "2023-02-07T10:45:26.798462";
        String requestTimeEnded = "2023-02-07T11:45:26.798462";
        Duration expectedTotalWorkoutTime = Duration.between(LocalDateTime.parse(requestTimeStarted),
                LocalDateTime.parse(requestTimeEnded));
        double zeroBodyWeight = 0;
        double expectedBodyWeight = 175.5;
        int expectedDeadlift = 300;
        int expectedSquat = 250;
        int expectedBenchPress = 200;
        int expectedBarbellRow = 175;
        int expectedOverheadPress = 125;
        List<Integer> expectedSquatReps = List.of(5,5,5,5,5);
        List<Integer> expectedBenchPressReps = new ArrayList<>();
        List<Integer> expectedBarbellRowReps = new ArrayList<>();
        List<Integer> expectedDeadliftReps = List.of(5);
        List<Integer> expectedOverheadPressReps = List.of(5,5,5,5,5);

        int expectedIncrementedSquat = 255;
        int expectedIncrementedOverheadPress = 130;
        int expectedIncrementedDeadlift = 310;

        User user = new User();
        user.setEmail(expectedEmail);
        user.setSquat(250);
        user.setOverheadPress(125);
        user.setDeadlift(300);
        user.setBodyWeight(expectedBodyWeight);

        when(userDao.getUser(expectedEmail)).thenReturn(user);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withWorkoutType(requestWorkoutType)
                .withWorkoutDate(requestWorkoutDate)
                .withTimeStarted(requestTimeStarted)
                .withTimeEnded(requestTimeEnded)
                .withBodyWeight(zeroBodyWeight)
                .withDeadliftWeight(expectedDeadlift)
                .withSquatWeight(expectedSquat)
                .withBenchPressWeight(expectedBenchPress)
                .withBarbellRowWeight(expectedBarbellRow)
                .withOverheadPressWeight(expectedOverheadPress)
                .withSquatReps(expectedSquatReps)
                .withBenchPressReps(expectedBenchPressReps)
                .withBarbellRowReps(expectedBarbellRowReps)
                .withDeadliftReps(expectedDeadliftReps)
                .withOverheadPressReps(expectedOverheadPressReps)
                .build();

        // WHEN
        AddWorkoutResult result = addWorkoutActivity.handleRequest(request);

        // THEN
        verify(workoutDao).saveWorkout(any(Workout.class));
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedEmail, result.getWorkout().getEmail());
        assertEquals(expectedWorkoutType, result.getWorkout().getWorkoutType());
        assertEquals(expectedWorkoutDate, result.getWorkout().getWorkoutDate());
        assertEquals(expectedTotalWorkoutTime, result.getWorkout().getTotalWorkoutTime());
        assertEquals(expectedBodyWeight, result.getWorkout().getBodyWeight());
        assertEquals(expectedDeadlift, result.getWorkout().getDeadliftWeight());
        assertEquals(expectedSquat, result.getWorkout().getSquatWeight());
        assertEquals(expectedBenchPress, result.getWorkout().getBenchPressWeight());
        assertEquals(expectedBarbellRow, result.getWorkout().getBarbellRowWeight());
        assertEquals(expectedOverheadPress, result.getWorkout().getOverheadPressWeight());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedSquatReps, result.getWorkout().getSquatReps());
        assertEquals(expectedBenchPressReps, result.getWorkout().getBenchPressReps());
        assertEquals(expectedOverheadPressReps, result.getWorkout().getOverheadPressReps());
        assertEquals(expectedDeadliftReps, result.getWorkout().getDeadliftReps());
        assertEquals(expectedBarbellRowReps, result.getWorkout().getBarbellRowReps());

        assertEquals(expectedIncrementedSquat, user.getSquat());
        assertEquals(expectedIncrementedOverheadPress, user.getOverheadPress());
        assertEquals(expectedIncrementedDeadlift, user.getDeadlift());
    }

    @Test
    public void handleRequest_withBodyWeightLessThanZero_throwsBodyWeightLessThanZeroException() {
        // GIVEN
        String expectedEmail = "expectedEmail";
        String requestWorkoutType = "Workout A";
        String requestWorkoutDate = "2023-02-07";
        String requestTimeStarted = "2023-02-07T10:45:26.798462";
        String requestTimeEnded = "2023-02-07T11:45:26.798462";
        double negativeBodyWeight = -1;
        int expectedDeadlift = 300;
        int expectedSquat = 250;
        int expectedBenchPress = 200;
        int expectedBarbellRow = 175;
        int expectedOverheadPress = 125;
        List<Integer> expectedSquatReps = List.of(5,5,5,5,5);
        List<Integer> expectedBenchPressReps = new ArrayList<>();
        List<Integer> expectedBarbellRowReps = new ArrayList<>();
        List<Integer> expectedDeadliftReps = List.of(5);
        List<Integer> expectedOverheadPressReps = List.of(5,5,5,5,5);

        AddWorkoutRequest request = AddWorkoutRequest.builder()
                .withEmail(expectedEmail)
                .withWorkoutType(requestWorkoutType)
                .withWorkoutDate(requestWorkoutDate)
                .withTimeStarted(requestTimeStarted)
                .withTimeEnded(requestTimeEnded)
                .withBodyWeight(negativeBodyWeight)
                .withDeadliftWeight(expectedDeadlift)
                .withSquatWeight(expectedSquat)
                .withBenchPressWeight(expectedBenchPress)
                .withBarbellRowWeight(expectedBarbellRow)
                .withOverheadPressWeight(expectedOverheadPress)
                .withSquatReps(expectedSquatReps)
                .withBenchPressReps(expectedBenchPressReps)
                .withBarbellRowReps(expectedBarbellRowReps)
                .withDeadliftReps(expectedDeadliftReps)
                .withOverheadPressReps(expectedOverheadPressReps)
                .build();

        // WHEN + THEN
        assertThrows(BodyWeightLessThanZeroException.class, () -> addWorkoutActivity.handleRequest(request));
    }
}
