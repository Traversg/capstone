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
     * @param userDao UserDao to access the users table.
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
        log.info("Received GetUpcomingWorkoutsRequest {}", getUpcomingWorkoutsRequest);

        Optional<Workout> mostRecentWorkoutOptional =
                workoutDao.getMostRecentWorkout(getUpcomingWorkoutsRequest.getEmail());

        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();

        WorkoutModel currentWorkout = createUpcomingWorkouts(mostRecentWorkoutOptional, getUpcomingWorkoutsRequest).get(0);
        upcomingWorkouts.add(currentWorkout);

        if (getUpcomingWorkoutsRequest.isCurrentWorkout() == null) {
            WorkoutModel workout2 = createUpcomingWorkouts(mostRecentWorkoutOptional, getUpcomingWorkoutsRequest).get(1);
            WorkoutModel workout3 = createUpcomingWorkouts(mostRecentWorkoutOptional, getUpcomingWorkoutsRequest).get(2);
            upcomingWorkouts.add(workout2);
            upcomingWorkouts.add(workout3);
        }

        return GetUpcomingWorkoutsResult.builder()
                .withUpcomingWorkouts(upcomingWorkouts)
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

    private List<WorkoutModel> createBABWorkoutSchedule(int mostRecentSquatWeight,
                                                        int mostRecentBenchPressWeight,
                                                        int mostRecentBarbellRowWeight,
                                                        int mostRecentOverheadPressWeight,
                                                        int mostRecentDeadliftWeight,
                                                        LocalDate dayToStart) {
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

    private List<WorkoutModel> createUpcomingWorkouts(Optional<Workout> mostRecentWorkout, GetUpcomingWorkoutsRequest request) {
        User userProfile = userDao.getUser(request.getEmail());
        int squatWeight = userProfile.getSquat();
        int benchPressWeight = userProfile.getBenchPress();
        int barbellRowWeight = userProfile.getBarbellRow();
        int overheadPressWeight = userProfile.getOverheadPress();
        int deadliftWeight = userProfile.getDeadlift();

        if (mostRecentWorkout.isEmpty()) {
            return createABAWorkoutSchedule(squatWeight, benchPressWeight, barbellRowWeight,
                    overheadPressWeight, deadliftWeight, LocalDate.now());
        } else {
            WorkoutType mostRecentWorkoutType = mostRecentWorkout.get().getWorkoutType();
            LocalDate mostRecentWorkoutDate = mostRecentWorkout.get().getWorkoutDate();

            if (mostRecentWorkoutType.equals(WorkoutType.WORKOUT_A)) {
                return createBABWorkoutSchedule(squatWeight, benchPressWeight, barbellRowWeight,
                        overheadPressWeight, deadliftWeight, dayToStartWorkout(mostRecentWorkoutDate));
            } else {
                return createABAWorkoutSchedule(squatWeight, benchPressWeight, barbellRowWeight,
                        overheadPressWeight, deadliftWeight, dayToStartWorkout(mostRecentWorkoutDate));
            }
        }
    }
}
