package com.nashss.se.fivelifts.converters;

import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.UserModel;
import com.nashss.se.fivelifts.models.WorkoutModel;

import org.junit.jupiter.api.Test;

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
        user.setBench(175);
        user.setDeadlift(275);
        user.setOverheadPress(125);
        user.setBarbellRow(150);

        UserModel userModel = modelConverter.toUserModel(user);
        assertEquals(user.getName(), userModel.getName());
        assertEquals(user.getEmail(), userModel.getEmail());
        assertEquals(user.getBodyWeight(), userModel.getBodyWeight());
        assertEquals(user.getSquat(), userModel.getSquat());
        assertEquals(user.getBench(), userModel.getBench());
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
        workout.setTimeStarted(new Date());
        workout.setTimeEnded(new Date());
        workout.setSquatWeight(225);
        workout.setBenchWeight(175);
        workout.setDeadliftWeight(275);
        workout.setOhpWeight(125);
        workout.setRowWeight(150);
        workout.setSquatReps(new ArrayList<>());
        workout.setBenchReps(new ArrayList<>());
        workout.setOhpReps(new ArrayList<>());
        workout.setRowReps(new ArrayList<>());
        workout.setDeadliftReps(new ArrayList<>());

        WorkoutModel workoutModel = modelConverter.toWorkoutModel(workout);
        assertEquals(workout.getEmail(), workoutModel.getEmail());
        assertEquals(workout.getWorkoutDate(), workoutModel.getDate());
        assertEquals(workout.getWorkoutType(), workoutModel.getWorkoutType());
        assertEquals(workout.getTimeStarted(), workoutModel.getTimeStarted());
        assertEquals(workout.getTimeEnded(), workoutModel.getTimeEnded());
        assertEquals(workout.getSquatWeight(), workoutModel.getSquatWeight());
        assertEquals(workout.getBenchWeight(), workoutModel.getBenchWeight());
        assertEquals(workout.getDeadliftWeight(), workoutModel.getDeadliftWeight());
        assertEquals(workout.getOhpWeight(), workoutModel.getOhpWeight());
        assertEquals(workout.getRowWeight(), workoutModel.getRowWeight());
        assertEquals(workout.getSquatReps(), workoutModel.getSquatReps());
        assertEquals(workout.getBenchReps(), workoutModel.getBenchReps());
        assertEquals(workout.getOhpReps(), workoutModel.getOhpReps());
        assertEquals(workout.getRowReps(), workoutModel.getOhpReps());
        assertEquals(workout.getDeadliftReps(), workoutModel.getDeadliftReps());
    }

}
