package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

public class GetMostRecentWorkoutResult {
    private final WorkoutModel mostRecentWorkout;

    private GetMostRecentWorkoutResult(WorkoutModel mostRecentWorkout) {
        this.mostRecentWorkout = mostRecentWorkout;
    }

    public WorkoutModel getMostRecentWorkout() {
        return mostRecentWorkout;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WorkoutModel mostRecentWorkout;

        public Builder withMostRecentWorkout(WorkoutModel mostRecentWorkout) {
            this.mostRecentWorkout = mostRecentWorkout;
            return this;
        }

        public GetMostRecentWorkoutResult build() {
            return new GetMostRecentWorkoutResult(mostRecentWorkout);
        }
    }
}
