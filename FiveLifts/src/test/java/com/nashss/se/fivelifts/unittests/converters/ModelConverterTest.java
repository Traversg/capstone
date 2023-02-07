package com.nashss.se.fivelifts.unittests.converters;

import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.UserModel;
import com.nashss.se.fivelifts.models.WorkoutModel;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toUserModel_withAllData_convertsUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
        user.setBodyWeight(175.0);
        user.setSquat(225);
        user.setBenchPress(175);
        user.setDeadlift(275);
        user.setOverheadPress(125);
        user.setBarbellRow(150);

        UserModel userModel = modelConverter.toUserModel(user);
        assertEquals(user.getName(), userModel.getName());
        assertEquals(user.getEmail(), userModel.getEmail());
        assertEquals(user.getBodyWeight(), userModel.getBodyWeight());
        assertEquals(user.getSquat(), userModel.getSquat());
        assertEquals(user.getBenchPress(), userModel.getBenchPress());
        assertEquals(user.getDeadlift(), userModel.getDeadlift());
        assertEquals(user.getOverheadPress(), userModel.getOverheadPress());
        assertEquals(user.getBarbellRow(), userModel.getBarbellRow());
    }

    @Test
    void toWorkoutModel_withAllData_convertsWorkout() {
        Workout workout = new Workout();
        workout.setEmail("email");
        workout.setWorkoutDate(LocalDate.now());
        workout.setWorkoutType(WorkoutType.WORKOUT_A);
        workout.setTotalWorkoutTime(Duration.ofMinutes(60));
        workout.setSquatWeight(225);
        workout.setBenchPressWeight(175);
        workout.setDeadliftWeight(275);
        workout.setOverheadPressWeight(125);
        workout.setBarbellRowWeight(150);
        workout.setSquatReps(new ArrayList<>());
        workout.setBenchPressReps(new ArrayList<>());
        workout.setOverheadPressReps(new ArrayList<>());
        workout.setBarbellRowReps(new ArrayList<>());
        workout.setDeadliftReps(new ArrayList<>());
        workout.setBodyWeight(170);

        WorkoutModel workoutModel = modelConverter.toWorkoutModel(workout);
        assertEquals(workout.getEmail(), workoutModel.getEmail());
        assertEquals(workout.getWorkoutDate(), workoutModel.getWorkoutDate());
        assertEquals(workout.getWorkoutType(), workoutModel.getWorkoutType());
        assertEquals(workout.getTotalWorkoutTime(), workoutModel.getTotalWorkoutTime());
        assertEquals(workout.getSquatWeight(), workoutModel.getSquatWeight());
        assertEquals(workout.getBenchPressWeight(), workoutModel.getBenchPressWeight());
        assertEquals(workout.getDeadliftWeight(), workoutModel.getDeadliftWeight());
        assertEquals(workout.getOverheadPressWeight(), workoutModel.getOverheadPressWeight());
        assertEquals(workout.getBarbellRowWeight(), workoutModel.getBarbellRowWeight());
        assertEquals(workout.getSquatReps(), workoutModel.getSquatReps());
        assertEquals(workout.getBenchPressReps(), workoutModel.getBenchPressReps());
        assertEquals(workout.getOverheadPressReps(), workoutModel.getOverheadPressReps());
        assertEquals(workout.getBarbellRowReps(), workoutModel.getBarbellRowReps());
        assertEquals(workout.getDeadliftReps(), workoutModel.getDeadliftReps());
        assertEquals(workout.getBodyWeight(), workoutModel.getBodyWeight());
    }

}
