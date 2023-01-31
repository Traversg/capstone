package com.nashss.se.fivelifts.models;

import java.util.Date;
import java.util.List;

/**
 * A model representation of a Workout object.
 */
public class WorkoutModel {
    private final String email;
    private final Date date;
    private final String workoutType;
    private final Date timeStarted;
    private final Date timeEnded;
    private final int squatWeight;
    private final int benchWeight;
    private final int ohpWeight;
    private final int rowWeight;
    private final int deadliftWeight;
    private final List<Integer> squatReps;
    private final List<Integer> benchReps;
    private final List<Integer> ohpReps;
    private final List<Integer> rowReps;
    private final List<Integer> deadliftReps;
    private final boolean isComplete;

    private WorkoutModel(String email, Date date, String workoutType, Date timeStarted,
                    Date timeEnded, int squatWeight, int benchWeight, int ohpWeight,
                    int rowWeight, int deadliftWeight, List<Integer> squatReps,
                    List<Integer> benchReps, List<Integer> ohpReps, List<Integer> rowReps,
                    List<Integer> deadliftReps, boolean isComplete) {
        this.email = email;
        this.date = date;
        this.workoutType = workoutType;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
        this.squatWeight = squatWeight;
        this.benchWeight = benchWeight;
        this.ohpWeight = ohpWeight;
        this.rowWeight = rowWeight;
        this.deadliftWeight = deadliftWeight;
        this.squatReps = squatReps;
        this.benchReps = benchReps;
        this.ohpReps = ohpReps;
        this.deadliftReps = deadliftReps;
        this.rowReps = rowReps;
        this.isComplete = isComplete;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public Date getTimeEnded() {
        return timeEnded;
    }

    public int getSquatWeight() {
        return squatWeight;
    }

    public int getBenchWeight() {
        return benchWeight;
    }

    public int getOhpWeight() {
        return ohpWeight;
    }

    public int getRowWeight() {
        return rowWeight;
    }

    public int getDeadliftWeight() {
        return deadliftWeight;
    }

    public List<Integer> getSquatReps() {
        return squatReps;
    }

    public List<Integer> getBenchReps() {
        return benchReps;
    }

    public List<Integer> getOhpReps() {
        return ohpReps;
    }

    public List<Integer> getRowReps() {
        return rowReps;
    }

    public List<Integer> getDeadliftReps() {
        return deadliftReps;
    }

    public boolean isComplete() {
        return isComplete;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private Date date;
        private String workoutType;
        private Date timeStarted;
        private Date timeEnded;
        private int squatWeight;
        private int benchWeight;
        private int ohpWeight;
        private int rowWeight;
        private int deadliftWeight;
        private List<Integer> squatReps;
        private List<Integer> benchReps;
        private List<Integer> ohpReps;
        private List<Integer> rowReps;
        private List<Integer> deadliftReps;
        private boolean isComplete;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withWorkoutType(String workoutType) {
            this.workoutType = workoutType;
            return this;
        }

        public Builder withTimeStarted(Date timeStarted) {
            this.timeStarted = timeStarted;
            return this;
        }

        public Builder withTimeEnded(Date timeEnded) {
            this.timeEnded = timeEnded;
            return this;
        }

        public Builder withSquatWeight(int squatWeight) {
            this.squatWeight = squatWeight;
            return this;
        }

        public Builder withBenchWeight(int benchWeight) {
            this.benchWeight = benchWeight;
            return this;
        }

        public Builder withOhpWeight(int ohpWeight) {
            this.ohpWeight = ohpWeight;
            return this;
        }

        public Builder withRowWeight(int rowWeight) {
            this.rowWeight = rowWeight;
            return this;
        }

        public Builder withDeadliftWeight(int deadliftWeight) {
            this.deadliftWeight = deadliftWeight;
            return this;
        }

        public Builder withSquatReps(List<Integer> squatReps) {
            this.squatReps = squatReps;
            return this;
        }

        public Builder withBenchReps(List<Integer> benchReps) {
            this.benchReps = benchReps;
            return this;
        }

        public Builder withOhpReps(List<Integer> ohpReps) {
            this.ohpReps = ohpReps;
            return this;
        }

        public Builder withRowReps(List<Integer> rowReps) {
            this.rowReps = rowReps;
            return this;
        }

        public Builder withDeadliftReps(List<Integer> deadliftReps) {
            this.deadliftReps = deadliftReps;
            return this;
        }

        public Builder withIsComplete(boolean isComplete) {
            this.isComplete = isComplete;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(email, date, workoutType, timeStarted, timeEnded, squatWeight,
                    benchWeight, ohpWeight, rowWeight, deadliftWeight, squatReps, benchReps, ohpReps,
                    rowReps, deadliftReps, isComplete);
        }
    }
}
