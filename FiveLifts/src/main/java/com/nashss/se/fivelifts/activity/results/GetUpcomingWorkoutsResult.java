package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetUpcomingWorkoutsResult for the FiveLifts' GetUpcomingWorkoutsResult API.
 */
public class GetUpcomingWorkoutsResult {
    private final List<WorkoutModel> upcomingWorkouts;

    private GetUpcomingWorkoutsResult(List<WorkoutModel> upcomingWorkouts) {
        this.upcomingWorkouts = upcomingWorkouts;
    }

    public List<WorkoutModel> getUpcomingWorkouts() {
        return new ArrayList<>(upcomingWorkouts);
    }

    @Override
    public String toString() {
        return "GetUpcomingWorkoutResult{" +
                "upcomingWorkouts=" + upcomingWorkouts +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<WorkoutModel> upcomingWorkouts;

        public Builder withUpcomingWorkouts(List<WorkoutModel> upcomingWorkouts) {
            this.upcomingWorkouts = new ArrayList<>(upcomingWorkouts);
            return this;
        }

        public GetUpcomingWorkoutsResult build() {
            return new GetUpcomingWorkoutsResult(upcomingWorkouts);
        }
    }
}
