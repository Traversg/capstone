package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetWorkoutHistoryResult for the FiveLifts' GetWorkoutHistoryResult API.
 */
public class GetWorkoutHistoryResult {
    private final List<WorkoutModel> workoutHistory;

    private GetWorkoutHistoryResult(List<WorkoutModel> workoutHistory) {
        this.workoutHistory = workoutHistory;
    }

    public List<WorkoutModel> getWorkoutHistory() {
        return new ArrayList<>(workoutHistory);
    }

    @Override
    public String toString() {
        return "GetWorkoutHistoryResult{" +
                "workoutHistory=" + workoutHistory +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<WorkoutModel> workoutHistory;

        public Builder withWorkoutHistory(List<WorkoutModel> workoutHistory) {
            this.workoutHistory = new ArrayList<>(workoutHistory);
            return this;
        }

        public GetWorkoutHistoryResult build() {
            return new GetWorkoutHistoryResult(workoutHistory);
        }
    }
}
