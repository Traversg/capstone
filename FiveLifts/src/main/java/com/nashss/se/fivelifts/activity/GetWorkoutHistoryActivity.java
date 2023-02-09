package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.GetWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.GetWorkoutHistoryResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.models.WorkoutModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Implementation of the GetWorkoutHistoryActivity for the FiveLifts' GetWorkoutHistory API.
 * <p>
 * This API allows the user to get their last 20 workouts.
 */
public class GetWorkoutHistoryActivity {
    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;


    /**
     * Instantiates a new GetWorkoutHistoryActivity object.
     *
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public GetWorkoutHistoryActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by retrieving the last 20 completed workouts from the database.
     * <p>
     * It then returns a list of those workouts.
     * <p>
     *
     * @param getWorkoutHistoryRequest request object containing the email
     * @return getWorkoutHistoryResult result object containing the API defined {@link WorkoutModel}
     */

    public GetWorkoutHistoryResult handleRequest(GetWorkoutHistoryRequest getWorkoutHistoryRequest) {
        log.info("Received GetWorkoutHistoryRequest {}", getWorkoutHistoryRequest);

        List<Workout> workoutHistory = workoutDao.getWorkoutHistory(getWorkoutHistoryRequest.getEmail());

        List<WorkoutModel> workoutHistoryModel = workoutHistory.stream()
                .map(workout -> new ModelConverter().toWorkoutModel(workout))
                .collect(Collectors.toList());

        return GetWorkoutHistoryResult.builder()
                .withWorkoutHistory(workoutHistoryModel)
                .build();
    }
}
