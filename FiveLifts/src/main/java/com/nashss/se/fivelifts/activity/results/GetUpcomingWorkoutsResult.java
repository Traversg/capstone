package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetUpcomingWorkoutsResult for the FiveLifts' GetUpcomingWorkoutsResult API.
 */
public class GetUpcomingWorkoutsResult {
    private final List<WorkoutModel> workouts;

    private GetUpcomingWorkoutsResult(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    public List<WorkoutModel> getWorkouts() {
        return new ArrayList<>(workouts);
    }

    @Override
    public String toString() {
        return "GetUpcomingWorkoutResult{" +
                "workouts=" + workouts +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<WorkoutModel> workouts;

        public Builder withWorkouts(List<WorkoutModel> workouts) {
            this.workouts = new ArrayList<>(workouts);
            return this;
        }

        public GetUpcomingWorkoutsResult build() {
            return new GetUpcomingWorkoutsResult(workouts);
        }
    }
}
