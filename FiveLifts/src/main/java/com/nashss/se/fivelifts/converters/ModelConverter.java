package com.nashss.se.fivelifts.converters;

import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.models.UserModel;
import com.nashss.se.fivelifts.models.WorkoutModel;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     *
     * @param user the user to convert
     * @return the converted user
     */
    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withBodyWeight(user.getBodyWeight())
                .withBarbellRow(user.getBarbellRow())
                .withBenchPress(user.getBenchPress())
                .withDeadlift(user.getDeadlift())
                .withOverheadPress(user.getOverheadPress())
                .withSquat(user.getSquat())
                .build();
    }

    /**
     * Converts a provided {@link Workout} into a {@link WorkoutModel} representation.
     *
     * @param workout the workout to convert
     * @return the converted workout
     */
    public WorkoutModel toWorkoutModel(Workout workout) {
        return WorkoutModel.builder()
                .withEmail(workout.getEmail())
                .withDate(workout.getWorkoutDate())
                .withWorkoutType(workout.getWorkoutType())
                .withTotalWorkoutTime(workout.getTotalWorkoutTime())
                .withSquatWeight(workout.getSquatWeight())
                .withBenchPressWeight(workout.getBenchPressWeight())
                .withOverheadPressWeight(workout.getOverheadPressWeight())
                .withBarbellRowWeight(workout.getBarbellRowWeight())
                .withDeadliftWeight(workout.getDeadliftWeight())
                .withSquatReps(workout.getSquatReps())
                .withBenchPressReps(workout.getBenchPressReps())
                .withOverheadPressReps(workout.getOverheadPressReps())
                .withBarbellRowReps(workout.getBarbellRowReps())
                .withDeadliftReps(workout.getDeadliftReps())
                .withBodyWeight(workout.getBodyWeight())
                .build();
    }
}
