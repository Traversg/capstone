package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.GetUpcomingWorkoutsActivity;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.WorkoutModel;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetUpcomingWorkoutActivityTest {
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
        String testEmail = "test@email.com";

        Workout mostRecentWorkout = new Workout();
        mostRecentWorkout.setEmail(testEmail);
        mostRecentWorkout.setWorkoutType(WorkoutType.WORKOUT_B);
        mostRecentWorkout.setSquatWeight(225);
        mostRecentWorkout.setBenchPressWeight(175);
        mostRecentWorkout.setBarbellRowWeight(150);
        mostRecentWorkout.setDeadliftWeight(275);
        mostRecentWorkout.setOverheadPressWeight(125);
        mostRecentWorkout.setWorkoutDate(LocalDate.of(2023, Month.JANUARY, 1));
        when(workoutDao.getMostRecentWorkout(testEmail)).thenReturn(mostRecentWorkout);

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
        WorkoutModel upcomingWo

        Workout upcomingWorkout3 = new Workout();
        upcomingWorkout3.setWorkoutDate(upcomingWorkout2.getWorkoutDate().plusDays(2));
        upcomingWorkout3.setWorkoutType(WorkoutType.WORKOUT_A);
        upcomingWorkout3.setSquatWeight(240);
        upcomingWorkout3.setBenchPressWeight(185);
        upcomingWorkout3.setBarbellRowWeight(160);

        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        upcomingWorkouts.add(upcomingWorkout1);
        upcomingWorkouts.add(upcomingWorkouts);
    }
}
