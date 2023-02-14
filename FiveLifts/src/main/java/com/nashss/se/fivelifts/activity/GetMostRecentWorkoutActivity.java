package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.GetMostRecentWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.GetMostRecentWorkoutResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.exceptions.WorkoutNotFoundException;
import com.nashss.se.fivelifts.metrics.MetricsConstants;
import com.nashss.se.fivelifts.metrics.MetricsPublisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetMostRecentWorkoutActivity
 * for the FiveLifts' GetMostRecentWorkout API.
 * <p>
 * This API retrieves the most recent workout from the workouts table.
 */
public class GetMostRecentWorkoutActivity {
    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new GetMostRecentWorkoutActivity object.
     *
     * @param workoutDao WorkoutDao to access the workouts table.
     * @param metricsPublisher to record metrics.
     */
    @Inject
    public GetMostRecentWorkoutActivity(WorkoutDao workoutDao,
                                        MetricsPublisher metricsPublisher) {
        this.workoutDao = workoutDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by
     * retrieving the most recent workout from the database.
     * <p>
     *
     * @param getMostRecentWorkoutRequest request object containing the email
     * @return getIsMostRecentWorkoutActivity result object containing the API defined {@link WorkoutModel}
     */
    public GetMostRecentWorkoutResult handleRequest(GetMostRecentWorkoutRequest
                                                        getMostRecentWorkoutRequest) {
        log.info("Received GetMostRecentWorkoutRequest {}", getMostRecentWorkoutRequest);
        String userEmail = getMostRecentWorkoutRequest.getEmail();

        if (workoutDao.getMostRecentWorkout(userEmail).isEmpty()) {
            metricsPublisher.addCount(MetricsConstants.GETMOSTRECENTWORKOUT_WORKOUTNOTFOUND_COUNT, 1);
            throw new WorkoutNotFoundException();
        }

        Workout workout = workoutDao.getMostRecentWorkout(userEmail).get();
        metricsPublisher.addCount(MetricsConstants.GETMOSTRECENTWORKOUT_WORKOUTNOTFOUND_COUNT, 0);

        return new GetMostRecentWorkoutResult.Builder()
                .withMostRecentWorkout(new ModelConverter().toWorkoutModel(workout))
                .build();
    }
}
