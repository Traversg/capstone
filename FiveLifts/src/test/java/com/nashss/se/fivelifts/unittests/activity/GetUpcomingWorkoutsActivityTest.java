package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.GetUpcomingWorkoutsActivity;
import com.nashss.se.fivelifts.activity.requests.GetUpcomingWorkoutsRequest;
import com.nashss.se.fivelifts.activity.results.GetUpcomingWorkoutsResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.WorkoutModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetUpcomingWorkoutsActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private WorkoutDao workoutDao;

    private GetUpcomingWorkoutsActivity getUpcomingWorkoutsActivity;

    @BeforeEach
    void setUp(){
        openMocks(this);
        getUpcomingWorkoutsActivity = new GetUpcomingWorkoutsActivity(workoutDao, userDao);
    }

    @Test
    public void handleRequest_withMostRecentWorkoutTypeB_returnsListOfABAWorkoutModel() {
        // GIVEN
        String testEmail = "test@email.com";

        Workout mostRecentWorkout = new Workout();
        mostRecentWorkout.setEmail(testEmail);
        mostRecentWorkout.setWorkoutType(WorkoutType.WORKOUT_B);
        mostRecentWorkout.setWorkoutDate(LocalDate.of(2023, Month.JANUARY, 1));
        when(workoutDao.getMostRecentWorkout(testEmail)).thenReturn(Optional.of(mostRecentWorkout));

        User currentUser = new User();
        currentUser.setSquat(225);
        currentUser.setBenchPress(175);
        currentUser.setBarbellRow(150);
        currentUser.setDeadlift(275);
        currentUser.setOverheadPress(125);
        when(userDao.getUser(testEmail)).thenReturn(currentUser);

        Workout upcomingWorkout1 = new Workout();
        upcomingWorkout1.setWorkoutDate(LocalDate.now());
        upcomingWorkout1.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout1.setSquatWeight(230);
        upcomingWorkout1.setBenchPressWeight(180);
        upcomingWorkout1.setBarbellRowWeight(155);
        WorkoutModel upcomingWorkoutModel1 = new ModelConverter().toWorkoutModel(upcomingWorkout1);

        Workout upcomingWorkout2 = new Workout();
        upcomingWorkout2.setWorkoutDate(upcomingWorkout1.getWorkoutDate().plusDays(2));
        upcomingWorkout2.setWorkoutType(WorkoutType.WORKOUT_B);
        upcomingWorkout2.setSquatWeight(235);
        upcomingWorkout2.setOverheadPressWeight(130);
        upcomingWorkout2.setDeadliftWeight(285);
        WorkoutModel upcomingWorkoutModel2 = new ModelConverter().toWorkoutModel(upcomingWorkout2);

        Workout upcomingWorkout3 = new Workout();
        upcomingWorkout3.setWorkoutDate(upcomingWorkout2.getWorkoutDate().plusDays(2));
        upcomingWorkout3.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout3.setSquatWeight(240);
        upcomingWorkout3.setBenchPressWeight(185);
        upcomingWorkout3.setBarbellRowWeight(160);
        WorkoutModel upcomingWorkoutModel3 = new ModelConverter().toWorkoutModel(upcomingWorkout3);

        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        upcomingWorkouts.add(upcomingWorkoutModel1);
        upcomingWorkouts.add(upcomingWorkoutModel2);
        upcomingWorkouts.add(upcomingWorkoutModel3);

        GetUpcomingWorkoutsRequest request = GetUpcomingWorkoutsRequest.builder()
                .withEmail(testEmail)
                .build();

        // WHEN
        GetUpcomingWorkoutsResult result = getUpcomingWorkoutsActivity.handleRequest(request);
        List<WorkoutModel> resultModels = result.getUpcomingWorkouts();

        // THEN
        assertEquals(upcomingWorkouts.get(0).getWorkoutDate(), resultModels.get(0).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(0).getWorkoutType(), resultModels.get(0).getWorkoutType());
        assertEquals(upcomingWorkouts.get(0).getSquatWeight(), resultModels.get(0).getSquatWeight());
        assertEquals(upcomingWorkouts.get(0).getBenchPressWeight(), resultModels.get(0).getBenchPressWeight());
        assertEquals(upcomingWorkouts.get(0).getBarbellRowWeight(), resultModels.get(0).getBarbellRowWeight());
        assertEquals(upcomingWorkouts.get(1).getWorkoutDate(), resultModels.get(1).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(1).getWorkoutType(), resultModels.get(1).getWorkoutType());
        assertEquals(upcomingWorkouts.get(1).getSquatWeight(), resultModels.get(1).getSquatWeight());
        assertEquals(upcomingWorkouts.get(1).getOverheadPressWeight(), resultModels.get(1).getOverheadPressWeight());
        assertEquals(upcomingWorkouts.get(1).getDeadliftWeight(), resultModels.get(1).getDeadliftWeight());
        assertEquals(upcomingWorkouts.get(2).getWorkoutDate(), resultModels.get(2).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(2).getWorkoutType(), resultModels.get(2).getWorkoutType());
        assertEquals(upcomingWorkouts.get(2).getSquatWeight(), resultModels.get(2).getSquatWeight());
        assertEquals(upcomingWorkouts.get(2).getBenchPressWeight(), resultModels.get(2).getBenchPressWeight());
        assertEquals(upcomingWorkouts.get(2).getBarbellRowWeight(), resultModels.get(2).getBarbellRowWeight());
    }

    @Test
    void handleRequest_withEmptyOptional_returnsABAWorkoutModel() {
        // GIVEN
        String testEmail = "test@email.com";

        User user = new User();
        user.setSquat(225);
        user.setBenchPress(175);
        user.setBarbellRow(150);
        user.setDeadlift(275);
        user.setOverheadPress(125);
        when(userDao.getUser(testEmail)).thenReturn(user);
        when(workoutDao.getMostRecentWorkout(testEmail)).thenReturn(Optional.empty());

        Workout upcomingWorkout1 = new Workout();
        upcomingWorkout1.setWorkoutDate(LocalDate.now());
        upcomingWorkout1.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout1.setSquatWeight(225);
        upcomingWorkout1.setBenchPressWeight(175);
        upcomingWorkout1.setBarbellRowWeight(150);
        WorkoutModel upcomingWorkoutModel1 = new ModelConverter().toWorkoutModel(upcomingWorkout1);

        Workout upcomingWorkout2 = new Workout();
        upcomingWorkout2.setWorkoutDate(upcomingWorkout1.getWorkoutDate().plusDays(2));
        upcomingWorkout2.setWorkoutType(WorkoutType.WORKOUT_B);
        upcomingWorkout2.setSquatWeight(230);
        upcomingWorkout2.setOverheadPressWeight(125);
        upcomingWorkout2.setDeadliftWeight(275);
        WorkoutModel upcomingWorkoutModel2 = new ModelConverter().toWorkoutModel(upcomingWorkout2);

        Workout upcomingWorkout3 = new Workout();
        upcomingWorkout3.setWorkoutDate(upcomingWorkout2.getWorkoutDate().plusDays(2));
        upcomingWorkout3.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout3.setSquatWeight(235);
        upcomingWorkout3.setBenchPressWeight(180);
        upcomingWorkout3.setBarbellRowWeight(155);
        WorkoutModel upcomingWorkoutModel3 = new ModelConverter().toWorkoutModel(upcomingWorkout3);

        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        upcomingWorkouts.add(upcomingWorkoutModel1);
        upcomingWorkouts.add(upcomingWorkoutModel2);
        upcomingWorkouts.add(upcomingWorkoutModel3);

        GetUpcomingWorkoutsRequest request = GetUpcomingWorkoutsRequest.builder()
                .withEmail(testEmail)
                .build();

        // WHEN
        GetUpcomingWorkoutsResult result = getUpcomingWorkoutsActivity.handleRequest(request);
        List<WorkoutModel> resultModels = result.getUpcomingWorkouts();

        // THEN
        assertEquals(upcomingWorkouts.get(0).getWorkoutDate(), resultModels.get(0).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(0).getWorkoutType(), resultModels.get(0).getWorkoutType());
        assertEquals(upcomingWorkouts.get(0).getSquatWeight(), resultModels.get(0).getSquatWeight());
        assertEquals(upcomingWorkouts.get(0).getBenchPressWeight(), resultModels.get(0).getBenchPressWeight());
        assertEquals(upcomingWorkouts.get(0).getBarbellRowWeight(), resultModels.get(0).getBarbellRowWeight());
        assertEquals(upcomingWorkouts.get(1).getWorkoutDate(), resultModels.get(1).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(1).getWorkoutType(), resultModels.get(1).getWorkoutType());
        assertEquals(upcomingWorkouts.get(1).getSquatWeight(), resultModels.get(1).getSquatWeight());
        assertEquals(upcomingWorkouts.get(1).getOverheadPressWeight(), resultModels.get(1).getOverheadPressWeight());
        assertEquals(upcomingWorkouts.get(1).getDeadliftWeight(), resultModels.get(1).getDeadliftWeight());
        assertEquals(upcomingWorkouts.get(2).getWorkoutDate(), resultModels.get(2).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(2).getWorkoutType(), resultModels.get(2).getWorkoutType());
        assertEquals(upcomingWorkouts.get(2).getSquatWeight(), resultModels.get(2).getSquatWeight());
        assertEquals(upcomingWorkouts.get(2).getBenchPressWeight(), resultModels.get(2).getBenchPressWeight());
        assertEquals(upcomingWorkouts.get(2).getBarbellRowWeight(), resultModels.get(2).getBarbellRowWeight());
    }

    @Test
    void handleRequest_withIsCurrentWorkoutIsTrue_returnFirstWorkout() {
        // GIVEN
        String testEmail = "test@email.com";

        Workout mostRecentWorkout = new Workout();
        mostRecentWorkout.setEmail(testEmail);
        mostRecentWorkout.setWorkoutType(WorkoutType.WORKOUT_B);
        mostRecentWorkout.setWorkoutDate(LocalDate.of(2023, Month.JANUARY, 1));
        when(workoutDao.getMostRecentWorkout(testEmail)).thenReturn(Optional.of(mostRecentWorkout));

        User currentUser = new User();
        currentUser.setSquat(225);
        currentUser.setBenchPress(175);
        currentUser.setBarbellRow(150);
        currentUser.setDeadlift(275);
        currentUser.setOverheadPress(125);
        when(userDao.getUser(testEmail)).thenReturn(currentUser);

        Workout upcomingWorkout1 = new Workout();
        upcomingWorkout1.setWorkoutDate(LocalDate.now());
        upcomingWorkout1.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout1.setSquatWeight(230);
        upcomingWorkout1.setBenchPressWeight(180);
        upcomingWorkout1.setBarbellRowWeight(155);
        WorkoutModel upcomingWorkoutModel1 = new ModelConverter().toWorkoutModel(upcomingWorkout1);

        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();
        upcomingWorkouts.add(upcomingWorkoutModel1);

        GetUpcomingWorkoutsRequest request = GetUpcomingWorkoutsRequest.builder()
                .withEmail(testEmail)
                .withCurrentWorkout("true")
                .build();

        // WHEN
        GetUpcomingWorkoutsResult result = getUpcomingWorkoutsActivity.handleRequest(request);
        List<WorkoutModel> resultModels = result.getUpcomingWorkouts();

        // THEN
        assertEquals(upcomingWorkouts.get(0).getWorkoutDate(), resultModels.get(0).getWorkoutDate());
        assertEquals(upcomingWorkouts.get(0).getWorkoutType(), resultModels.get(0).getWorkoutType());
        assertEquals(upcomingWorkouts.get(0).getSquatWeight(), resultModels.get(0).getSquatWeight());
        assertEquals(upcomingWorkouts.get(0).getBenchPressWeight(), resultModels.get(0).getBenchPressWeight());
        assertEquals(upcomingWorkouts.get(0).getBarbellRowWeight(), resultModels.get(0).getBarbellRowWeight());
    }
}
