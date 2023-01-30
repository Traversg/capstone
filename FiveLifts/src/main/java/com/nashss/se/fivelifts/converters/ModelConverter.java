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
                .withId(user.getId())
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withBodyWeight(user.getBodyWeight())
                .withBarbellRow(user.getBarbellRow())
                .withBench(user.getBench())
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
                .withUserId(workout.getUserId())
                .withDate(workout.getDate())
                .withWorkoutType(workout.getWorkoutType())
                .withTimeStarted(workout.getTimeStarted())
                .withTimeEnded(workout.getTimeEnded())
                .withSquatWeight(workout.getSquatWeight())
                .withBenchWeight(workout.getBenchWeight())
                .withOhpWeight(workout.getOhpWeight())
                .withRowWeight(workout.getRowWeight())
                .withDeadliftWeight(workout.getDeadliftWeight())
                .withSquatReps(workout.getSquatReps())
                .withBenchReps(workout.getBenchReps())
                .withOhpReps(workout.getOhpReps())
                .withRowReps(workout.getRowReps())
                .withDeadliftReps(workout.getDeadliftReps())
                .withIsComplete(workout.isComplete())
                .build();
    }
}
