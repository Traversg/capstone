package com.nashss.se.fivelifts.models;

import com.nashss.se.fivelifts.enums.WorkoutType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * A model representation of a Workout object.
 */
public class WorkoutModel {
    private final String email;
    private final LocalDate date;
    private final WorkoutType workoutType;
    private final Date timeStarted;
    private final Date timeEnded;
    private final int squatWeight;
    private final int benchPressWeight;
    private final int overheadPressWeight;
    private final int barbellRowWeight;
    private final int deadliftWeight;
    private final List<Integer> squatReps;
    private final List<Integer> benchPressReps;
    private final List<Integer> overheadPressReps;
    private final List<Integer> barbellRowReps;
    private final List<Integer> deadliftReps;

    private WorkoutModel(String email, LocalDate date, WorkoutType workoutType, Date timeStarted,
                         Date timeEnded, int squatWeight, int benchPressWeight, int overheadPressWeight,
                         int barbellRowWeight, int deadliftWeight, List<Integer> squatReps,
                         List<Integer> benchPressReps, List<Integer> overheadPressReps, List<Integer> barbellRowReps,
                         List<Integer> deadliftReps) {
        this.email = email;
        this.date = date;
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
        this.deadliftReps = deadliftReps;
        this.barbellRowReps = barbellRowReps;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate() {
        return date;
    }

    public WorkoutType getWorkoutType() {
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
        return squatReps;
    }

    public List<Integer> getBenchPressReps() {
        return benchPressReps;
    }

    public List<Integer> getOverheadPressReps() {
        return overheadPressReps;
    }

    public List<Integer> getBarbellRowReps() {
        return barbellRowReps;
    }

    public List<Integer> getDeadliftReps() {
        return deadliftReps;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private LocalDate date;
        private WorkoutType workoutType;
        private Date timeStarted;
        private Date timeEnded;
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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withWorkoutType(WorkoutType workoutType) {
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
            this.squatReps = squatReps;
            return this;
        }

        public Builder withBenchPressReps(List<Integer> benchPressReps) {
            this.benchPressReps = benchPressReps;
            return this;
        }

        public Builder withOverheadPressReps(List<Integer> overheadPressReps) {
            this.overheadPressReps = overheadPressReps;
            return this;
        }

        public Builder withBarbellRowReps(List<Integer> barbellRowReps) {
            this.barbellRowReps = barbellRowReps;
            return this;
        }

        public Builder withDeadliftReps(List<Integer> deadliftReps) {
            this.deadliftReps = deadliftReps;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(email, date, workoutType, timeStarted, timeEnded, squatWeight,
                    benchPressWeight, overheadPressWeight, barbellRowWeight, deadliftWeight, squatReps, benchPressReps, overheadPressReps,
                    barbellRowReps, deadliftReps);
        }
    }
}
