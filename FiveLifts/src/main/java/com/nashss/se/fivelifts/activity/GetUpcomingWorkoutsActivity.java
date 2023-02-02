package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.GetUpcomingWorkoutsRequest;
import com.nashss.se.fivelifts.activity.results.GetUpcomingWorkoutsResult;

import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
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
import javax.inject.Inject;

/**
 * Implementation of the GetUpcomingWorkoutsActivity for the FiveLifts' CreateProfile API.
 * <p>
 * This API allows the user to get their next three upcoming workouts.
 */
public class GetUpcomingWorkoutsActivity {
    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new GetUpcomingWorkoutsActivity object.
     *
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public GetUpcomingWorkoutsActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
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
        Workout latestWorkout = workoutDao.getLatestCompletedWorkout(getUpcomingWorkoutsRequest.getEmail());
        List<WorkoutModel> upcomingWorkouts = new ArrayList<>();
        WorkoutType latestWorkoutType = latestWorkout.getWorkoutType();

        int latestSquatWeight = latestWorkout.getSquatWeight();
        int latestBenchPressWeight = latestWorkout.getBenchWeight();
        int latestDeadliftWeight = latestWorkout.getDeadliftWeight();
        int latestOverheadPressWeight = latestWorkout.getOhpWeight();
        int latestBarbellRowWeight = latestWorkout.getRowWeight();
        LocalDate dayToStart = dayToStartWorkout(latestWorkout.getWorkoutDate());

        if (latestWorkoutType == WorkoutType.WORKOUT_A) {
            Workout workout1 = createWorkoutB(latestSquatWeight, latestOverheadPressWeight,
                    latestDeadliftWeight, dayToStart);
            Workout workout2 = createWorkoutA(workout1.getSquatWeight(), latestBenchPressWeight,
                    latestBarbellRowWeight, dayToStart.plus(Period.ofDays(2)));
            Workout workout3 = createWorkoutB(workout2.getSquatWeight(), workout1.getOhpWeight(),
                    workout1.getDeadliftWeight(), workout2.getWorkoutDate().plus(Period.ofDays(2)));

            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout1));
            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout2));
            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout3));
        }

        if (latestWorkoutType == WorkoutType.WORKOUT_B) {
            Workout workout1 = createWorkoutA(latestSquatWeight, latestBenchPressWeight,
                    latestBarbellRowWeight, dayToStart);
            Workout workout2 = createWorkoutB(workout1.getSquatWeight(), latestOverheadPressWeight,
                    latestDeadliftWeight, dayToStart.plus(Period.ofDays(2)));
            Workout workout3 = createWorkoutA(workout2.getSquatWeight(), workout1.getBenchWeight(),
                    workout1.getRowWeight(), workout2.getWorkoutDate().plus(Period.ofDays(2)));

            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout1));
            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout2));
            upcomingWorkouts.add(new ModelConverter().toWorkoutModel(workout3));
        }

        return GetUpcomingWorkoutsResult.builder()
                .withWorkouts(upcomingWorkouts)
                .build();
    }

    private LocalDate dayToStartWorkout(LocalDate dateOfLastCompleteWorkout) {
        if (dateOfLastCompleteWorkout.plus(Period.ofDays(1)).getDayOfYear() == LocalDate.now().getDayOfYear()) {
            return dateOfLastCompleteWorkout.plus(Period.ofDays(1));
        }
        return LocalDate.now();
    }

    private Workout createWorkoutA(int squatWeight, int benchPressWeight, int barbellRowWeight, LocalDate dayToStart) {
        Workout workout = new Workout();
        workout.setSquatWeight(squatWeight + Increments.squat());
        workout.setBenchWeight(benchPressWeight + Increments.benchPress());
        workout.setRowWeight(barbellRowWeight + Increments.barbellRow());
        workout.setWorkoutDate(dayToStart);
        workout.setWorkoutType(WorkoutType.WORKOUT_A);
        return workout;
    }

    private Workout createWorkoutB(int squatWeight, int overheadPress, int deadlift, LocalDate dayToStart) {
        Workout workout = new Workout();
        workout.setSquatWeight(squatWeight + Increments.squat());
        workout.setOhpWeight(overheadPress + Increments.overheadPress());
        workout.setDeadliftWeight(deadlift + Increments.deadlift());
        workout.setWorkoutDate(dayToStart);
        workout.setWorkoutType(WorkoutType.WORKOUT_B);
        return workout;
    }
}
