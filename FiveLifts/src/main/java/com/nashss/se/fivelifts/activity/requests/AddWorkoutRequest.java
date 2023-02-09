package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static com.nashss.se.fivelifts.utils.CollectionUtils.copyToList;

/**
 * Implementation of the AddWorkoutRequest for the FiveLifts' AddWorkout API.
 */
@JsonDeserialize(builder = AddWorkoutRequest.Builder.class)
public class AddWorkoutRequest {
    private String email;
    private String workoutDate;
    private String workoutType;
    private String timeStarted;
    private String timeEnded;
    private int squatWeight;
    private int benchPressWeight;
    private int overheadPressWeight;
    private int barbellRowWeight;
    private int deadliftWeight;
    private List<Integer> squatReps;
    private List<Integer> benchPressReps;
    private List<Integer> overheadPressReps;
    private List<Integer> barbellRowReps;
    private List<Integer> deadliftReps;
    private double bodyWeight;

    private AddWorkoutRequest(String email, String workoutDate, String workoutType,
              String timeStarted, String timeEnded, int squatWeight, int benchPressWeight,
              int overheadPressWeight, int barbellRowWeight, int deadliftWeight, List<Integer> squatReps,
              List<Integer> benchPressReps, List<Integer> overheadPressReps, List<Integer> barbellRowReps,
              List<Integer> deadliftReps, double bodyWeight) {
        this.email = email;
        this.workoutDate = workoutDate;
        this.workoutType = workoutType;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
        this.squatWeight = squatWeight;
        this.benchPressWeight = benchPressWeight;
        this.overheadPressWeight = overheadPressWeight;
        this.barbellRowWeight = barbellRowWeight;
        this.deadliftWeight = deadliftWeight;
        this.squatReps = squatReps;
        this.benchPressReps = benchPressReps;
        this.overheadPressReps = overheadPressReps;
        this.barbellRowReps = barbellRowReps;
        this.deadliftReps = deadliftReps;
        this.bodyWeight = bodyWeight;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public String getTimeEnded() {
        return timeEnded;
    }

    public int getSquatWeight() {
        return squatWeight;
    }

    public int getBenchPressWeight() {
        return benchPressWeight;
    }

    public int getOverheadPressWeight() {
        return overheadPressWeight;
    }

    public int getBarbellRowWeight() {
        return barbellRowWeight;
    }

    public int getDeadliftWeight() {
        return deadliftWeight;
    }

    public List<Integer> getSquatReps() {
        return copyToList(squatReps);
    }

    public List<Integer> getBenchPressReps() {
        return copyToList(benchPressReps);
    }

    public List<Integer> getOverheadPressReps() {
        return copyToList(overheadPressReps);
    }

    public List<Integer> getBarbellRowReps() {
        return copyToList(barbellRowReps);
    }

    public List<Integer> getDeadliftReps() {
        return copyToList(deadliftReps);
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    @Override
    public String toString() {
        return "AddWorkoutRequest{" +
                "email='" + email + '\'' +
                ", workoutDate='" + workoutDate + '\'' +
                ", workoutType='" + workoutType + '\'' +
                ", timeStarted='" + timeStarted + '\'' +
                ", timeEnded='" + timeEnded + '\'' +
                ", squatWeight=" + squatWeight + '\'' +
                ", benchPressWeight=" + benchPressWeight + '\'' +
                ", overheadPressWeight=" + overheadPressWeight + '\'' +
                ", barbellRowWeight=" + barbellRowWeight + '\'' +
                ", deadliftWeight=" + deadliftWeight + '\'' +
                ", squatReps=" + squatReps + '\'' +
                ", benchPressReps=" + benchPressReps + '\'' +
                ", overheadPressReps=" + overheadPressReps + '\'' +
                ", barbellRowReps=" + barbellRowReps + '\'' +
                ", deadliftReps=" + deadliftReps + '\'' +
                ", bodyWeight=" + bodyWeight +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String workoutDate;
        private String workoutType;
        private String timeStarted;
        private String timeEnded;
        private int squatWeight;
        private int benchPressWeight;
        private int overheadPressWeight;
        private int barbellRowWeight;
        private int deadliftWeight;
        private List<Integer> squatReps;
        private List<Integer> benchPressReps;
        private List<Integer> overheadPressReps;
        private List<Integer> barbellRowReps;
        private List<Integer> deadliftReps;
        private double bodyWeight;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withWorkoutDate(String workoutDate) {
            this.workoutDate = workoutDate;
            return this;
        }

        public Builder withWorkoutType(String workoutType) {
            this.workoutType = workoutType;
            return this;
        }

        public Builder withTimeStarted(String timeStarted) {
            this.timeStarted = timeStarted;
            return this;
        }

        public Builder withTimeEnded(String timeEnded) {
            this.timeEnded = timeEnded;
            return this;
        }

        public Builder withSquatWeight(int squatWeight) {
            this.squatWeight = squatWeight;
            return this;
        }

        public Builder withBenchPressWeight(int benchPressWeight) {
            this.benchPressWeight = benchPressWeight;
            return this;
        }

        public Builder withOverheadPressWeight(int overheadPressWeight) {
            this.overheadPressWeight = overheadPressWeight;
            return this;
        }

        public Builder withBarbellRowWeight(int barbellRowWeight) {
            this.barbellRowWeight = barbellRowWeight;
            return this;
        }

        public Builder withDeadliftWeight(int deadliftWeight) {
            this.deadliftWeight = deadliftWeight;
            return this;
        }

        public Builder withSquatReps(List<Integer> squatReps) {
            this.squatReps = copyToList(squatReps);
            return this;
        }

        public Builder withBenchPressReps(List<Integer> benchPressReps) {
            this.benchPressReps = copyToList(benchPressReps);
            return this;
        }

        public Builder withOverheadPressReps(List<Integer> overheadPressReps) {
            this.overheadPressReps = copyToList(overheadPressReps);
            return this;
        }

        public Builder withBarbellRowReps(List<Integer> barbellRowReps) {
            this.barbellRowReps = copyToList(barbellRowReps);
            return this;
        }

        public Builder withDeadliftReps(List<Integer> deadliftReps) {
            this.deadliftReps = copyToList(deadliftReps);
            return this;
        }

        public Builder withBodyWeight(double bodyWeight) {
            this.bodyWeight = bodyWeight;
            return this;
        }

        public AddWorkoutRequest build() {
            return new AddWorkoutRequest(email, workoutDate, workoutType, timeStarted,
                    timeEnded, squatWeight, benchPressWeight, overheadPressWeight, barbellRowWeight, deadliftWeight,
                    squatReps, benchPressReps, overheadPressReps, barbellRowReps, deadliftReps, bodyWeight);
        }
    }
}
