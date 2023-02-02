package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.GetUpcomingWorkoutsRequest;
import com.nashss.se.fivelifts.activity.results.GetUpcomingWorkoutsResult;

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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

/**
 * Implementation of the GetUpcomingWorkoutsActivity for the FiveLifts' CreateProfile API.
 * <p>
 * This API allows the user to get their next three upcoming workouts.
 */
public class GetUpcomingWorkoutsActivity {
    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;
    private final UserDao userDao;

    /**
     * Instantiates a new GetUpcomingWorkoutsActivity object.
     *
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public GetUpcomingWorkoutsActivity(WorkoutDao workoutDao, UserDao userDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by retrieving the latest completed workout from the database.
     * <p>
     * It then returns a list of the next three workouts to be completed
     * <p>
     *
     * @param getUpcomingWorkoutsRequest request object containing the email
     * @return getUpcomingWorkoutResult result object containing the API defined {@link WorkoutModel}
     */
    public GetUpcomingWorkoutsResult handleRequest(GetUpcomingWorkoutsRequest getUpcomingWorkoutsRequest) {
        Workout mostRecentWorkout = workoutDao.getMostRecentWorkout(getUpcomingWorkoutsRequest.getEmail());
        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        if (mostRecentWorkout == null) {
            User user = userDao.getUser(getUpcomingWorkoutsRequest.getEmail());
            int startingSquatWeight = user.getSquat();
            int startingBenchPressWeight = user.getBenchPress();
            int startingDeadliftWeight = user.getDeadlift();
            int startingOverheadPress = user.getOverheadPress();
            int startingBarbellRow = user.getBarbellRow();
            LocalDate dayToStart = LocalDate.now();

            upcomingWorkouts = createABAWorkoutSchedule(startingSquatWeight, startingBenchPressWeight, startingBarbellRow,
                    startingOverheadPress, startingDeadliftWeight, dayToStart);
        } else {
            WorkoutType mostRecentWorkoutType = mostRecentWorkout.getWorkoutType();

            int mostRecentSquatWeight = mostRecentWorkout.getSquatWeight();
            int mostRecentBenchPressWeight = mostRecentWorkout.getBenchPressWeight();
            int mostRecentDeadliftWeight = mostRecentWorkout.getDeadliftWeight();
            int mostRecentOverheadPressWeight = mostRecentWorkout.getOverheadPressWeight();
            int mostRecentBarbellRowWeight = mostRecentWorkout.getBarbellRowWeight();
            LocalDate dayToStart = dayToStartWorkout(mostRecentWorkout.getWorkoutDate());

            if (mostRecentWorkoutType == WorkoutType.WORKOUT_A) {
                upcomingWorkouts = createBABWorkoutSchedule(mostRecentSquatWeight, mostRecentBenchPressWeight,
                        mostRecentBarbellRowWeight, mostRecentOverheadPressWeight, mostRecentDeadliftWeight,
                        dayToStart);
            }

            if (mostRecentWorkoutType == WorkoutType.WORKOUT_B) {
                upcomingWorkouts = createABAWorkoutSchedule(mostRecentSquatWeight, mostRecentBenchPressWeight,
                        mostRecentBarbellRowWeight, mostRecentOverheadPressWeight, mostRecentDeadliftWeight,
                        dayToStart);
            }
        }

        return GetUpcomingWorkoutsResult.builder()
                .withWorkouts(upcomingWorkouts)
                .build();
    }

    private LocalDate dayToStartWorkout(LocalDate mostRecentWorkoutDate) {
        if (mostRecentWorkoutDate.plusDays(1).getDayOfYear() == LocalDate.now().getDayOfYear()) {
            return mostRecentWorkoutDate.plusDays(2);
        }
        return LocalDate.now();
    }

    private Workout createWorkoutA(int squatWeight, int benchPressWeight, int barbellRowWeight, LocalDate dayToStart) {
        Workout workout = new Workout();
        workout.setSquatWeight(squatWeight + Increments.squat());
        workout.setBenchPressWeight(benchPressWeight + Increments.benchPress());
        workout.setBarbellRowWeight(barbellRowWeight + Increments.barbellRow());
        workout.setWorkoutDate(dayToStart);
        workout.setWorkoutType(WorkoutType.WORKOUT_A);
        return workout;
    }

    private Workout createWorkoutB(int squatWeight, int overheadPress, int deadlift, LocalDate dayToStart) {
        Workout workout = new Workout();
        workout.setSquatWeight(squatWeight + Increments.squat());
        workout.setOverheadPressWeight(overheadPress + Increments.overheadPress());
        workout.setDeadliftWeight(deadlift + Increments.deadlift());
        workout.setWorkoutDate(dayToStart);
        workout.setWorkoutType(WorkoutType.WORKOUT_B);
        return workout;
    }

    private List<WorkoutModel> createABAWorkoutSchedule(int mostRecentSquatWeight, int mostRecentBenchPressWeight,
                                          int mostRecentBarbellRowWeight, int mostRecentOverheadPressWeight,
                                          int mostRecentDeadliftWeight, LocalDate dayToStart) {
        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        Workout workout1 = createWorkoutA(mostRecentSquatWeight, mostRecentBenchPressWeight,
                mostRecentBarbellRowWeight, dayToStart);

        Workout workout2 = createWorkoutB(workout1.getSquatWeight(), mostRecentOverheadPressWeight,
                mostRecentDeadliftWeight, dayToStart.plusDays(2));

        Workout workout3 = createWorkoutA(workout2.getSquatWeight(), workout1.getBenchPressWeight(),
                workout1.getBarbellRowWeight(), workout2.getWorkoutDate().plusDays(2));

        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout1));
        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout2));
        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout3));

        return upcomingWorkouts;
    }

    private List<WorkoutModel> createBABWorkoutSchedule(int mostRecentSquatWeight, int mostRecentBenchPressWeight,
                                                        int mostRecentBarbellRowWeight, int mostRecentOverheadPressWeight,
                                                        int mostRecentDeadliftWeight, LocalDate dayToStart) {
        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        Workout workout1 = createWorkoutB(mostRecentSquatWeight, mostRecentOverheadPressWeight,
                mostRecentDeadliftWeight, dayToStart);
        Workout workout2 = createWorkoutA(workout1.getSquatWeight(), mostRecentBenchPressWeight,
                mostRecentBarbellRowWeight, dayToStart.plusDays(2));
        Workout workout3 = createWorkoutB(workout2.getSquatWeight(), workout1.getOverheadPressWeight(),
                workout1.getDeadliftWeight(), workout2.getWorkoutDate().plusDays(2));

        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout1));
        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout2));
        upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout3));

        return upcomingWorkouts;
    }

}
