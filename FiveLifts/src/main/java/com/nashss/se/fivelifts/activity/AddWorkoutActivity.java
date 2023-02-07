package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.AddWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.AddWorkoutResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.WorkoutModel;
import com.nashss.se.fivelifts.utils.Increments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the AddWorkoutActivity for the FiveLifts' AddWorkout API.
 * <p>
 * This API allows the user to add a complete workout to the workout table.
 */
public class AddWorkoutActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new AddWorkoutActivity object.
     *
     * @param userDao UserDao to access the users table.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public AddWorkoutActivity(WorkoutDao workoutDao, UserDao userDao) {
        this.userDao = userDao;
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by persisting a new workout
     * with the provided email, body weight, reps, and lift data from the request. User profile
     * is also updated with body weight and weight lift data.
     * <p>
     * It then returns the newly added workout.
     * <p>
     *
     * @param addWorkoutRequest request object containing the user name, body weight, reps, and lift data
     *                              associated with it
     * @return addWorkoutResult result object containing the API defined {@link WorkoutModel}
     */
    public AddWorkoutResult handleRequest(final AddWorkoutRequest addWorkoutRequest) {
        log.info("Received AddWorkoutRequest {}", addWorkoutRequest);

        WorkoutType workoutType = (addWorkoutRequest.getWorkoutType().equals("Workout A")) ?
                WorkoutType.WORKOUT_A : WorkoutType.WORKOUT_B;

        LocalDateTime timeStarted = LocalDateTime.parse(addWorkoutRequest.getTimeStarted());
        LocalDateTime timeEnded = LocalDateTime.parse(addWorkoutRequest.getTimeEnded());

        Workout workout = new Workout();
        workout.setEmail(addWorkoutRequest.getEmail());
        workout.setWorkoutDate(LocalDate.parse(addWorkoutRequest.getWorkoutDate()));
        workout.setWorkoutType(workoutType);
        workout.setTotalWorkoutTime(Duration.between(timeStarted, timeEnded));
        workout.setSquatWeight(addWorkoutRequest.getSquatWeight());
        workout.setBenchPressWeight(addWorkoutRequest.getBenchPressWeight());
        workout.setOverheadPressWeight(addWorkoutRequest.getOverheadPressWeight());
        workout.setBarbellRowWeight(addWorkoutRequest.getBarbellRowWeight());
        workout.setDeadliftWeight(addWorkoutRequest.getDeadliftWeight());
        workout.setSquatReps(addWorkoutRequest.getSquatReps());
        workout.setBenchPressReps(addWorkoutRequest.getBenchPressReps());
        workout.setOverheadPressReps(addWorkoutRequest.getOverheadPressReps());
        workout.setBarbellRowReps(addWorkoutRequest.getBarbellRowReps());
        workout.setDeadliftReps(addWorkoutRequest.getDeadliftReps());
        workout.setBodyWeight(addWorkoutRequest.getBodyWeight());

        User user = userDao.getUser(addWorkoutRequest.getEmail());
        user.setBodyWeight(addWorkoutRequest.getBodyWeight());
        List<Integer> squatReps = addWorkoutRequest.getSquatReps();
        if (!squatReps.isEmpty()) {
            if (canIncrement(getReps(squatReps))) {
                user.setSquat(addWorkoutRequest.getSquatWeight() +
                        Increments.squat());
            } else {
                user.setSquat(addWorkoutRequest.getSquatWeight());
            }
        }

        List<Integer> benchPressReps = addWorkoutRequest.getBenchPressReps();
        if (!benchPressReps.isEmpty()) {
            if (canIncrement(getReps(benchPressReps))) {
                user.setBenchPress(addWorkoutRequest.getBenchPressWeight() + Increments.benchPress());
            } else {
                user.setBenchPress(addWorkoutRequest.getBenchPressWeight());
            }
        }

        List<Integer> overheadPressReps = addWorkoutRequest.getOverheadPressReps();
        if (!overheadPressReps.isEmpty()) {
            if (canIncrement(getReps(overheadPressReps))) {
                user.setOverheadPress(addWorkoutRequest.getOverheadPressWeight() + Increments.overheadPress());
            } else {
                user.setOverheadPress(addWorkoutRequest.getOverheadPressWeight());
            }
        }

        List<Integer> barbellRowReps = addWorkoutRequest.getBarbellRowReps();
        if (!barbellRowReps.isEmpty()) {
            if (canIncrement(getReps(barbellRowReps))) {
                user.setBarbellRow(addWorkoutRequest.getBarbellRowWeight() + Increments.barbellRow());
            } else {
                user.setBarbellRow(addWorkoutRequest.getBarbellRowWeight());
            }
        }

        List<Integer> deadliftReps = addWorkoutRequest.getDeadliftReps();
        if (!deadliftReps.isEmpty()) {
            if (canIncrementDeadlift(getReps(deadliftReps))) {
                user.setDeadlift(addWorkoutRequest.getDeadliftWeight() + Increments.deadlift());
            } else {
                user.setDeadlift(addWorkoutRequest.getDeadliftWeight());
            }
        }

        userDao.saveUser(user);
        workoutDao.saveWorkout(workout);

        return AddWorkoutResult.builder()
                .withWorkout(new ModelConverter().toWorkoutModel(workout))
                .build();
    }

    private boolean canIncrement(int reps) {
        int repsRequiredToIncrement = 25;
        return reps == repsRequiredToIncrement;
    }

    private boolean canIncrementDeadlift(int reps) {
        int repsRequiredToIncrement = 5;
        return reps == repsRequiredToIncrement;
    }

    private int getReps(List<Integer> reps) {
        return reps.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
