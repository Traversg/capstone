package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

/**
 * Implementation of the AddWorkoutResult for the FiveLifts' AddWorkout API.
 */
public class AddWorkoutResult {
    private final WorkoutModel workout;

    private AddWorkoutResult(WorkoutModel workout) {
        this.workout = workout;
    }

    public WorkoutModel getWorkout() {
        return workout;
    }

    @Override
    public String toString() {
        return "AddWorkoutResult{" +
                "workout=" + workout +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WorkoutModel workout;

        public Builder withWorkout(WorkoutModel workout) {
            this.workout = workout;
            return this;
        }

        public AddWorkoutResult build() {
            return new AddWorkoutResult(workout);
        }
    }
}
