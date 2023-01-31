package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

import java.util.List;

/**
 * Implementation of the GetUpcomingWorkoutResult for the FiveLifts' GetUpcomingWorkoutResult API.
 */
public class GetUpcomingWorkoutResult {
    private final List<WorkoutModel> workouts;

    private GetUpcomingWorkoutResult(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    public List<WorkoutModel> getWorkouts() {
        return workouts;
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
            this.workouts = workouts;
            return this;
        }

        public GetUpcomingWorkoutResult build() {
            return new GetUpcomingWorkoutResult(workouts);
        }
    }
}
