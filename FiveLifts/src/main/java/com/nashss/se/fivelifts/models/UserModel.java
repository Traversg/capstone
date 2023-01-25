package com.nashss.se.fivelifts.models;

import java.util.Objects;

public class UserModel {
    private final String id;
    private final String userName;
    private final double bodyWeight;
    private final int deadlift;
    private final int squat;
    private final int bench;
    private final int overheadPress;
    private final int barbellRow;

    private UserModel(String id, String userName, double bodyWeight, int deadlift,
                      int squat, int bench, int overheadPress, int barbellRow) {
        this.id = id;
        this.userName = userName;
        this.bodyWeight = bodyWeight;
        this.deadlift = deadlift;
        this.squat = squat;
        this.bench = bench;
        this.overheadPress = overheadPress;
        this.barbellRow = barbellRow;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public int getDeadlift() {
        return deadlift;
    }

    public int getSquat() {
        return squat;
    }

    public int getBench() {
        return bench;
    }

    public int getOverheadPress() {
        return overheadPress;
    }

    public int getBarbellRow() {
        return barbellRow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(userName, userModel.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String userName;
        private double bodyWeight;
        private int deadlift;
        private int squat;
        private int bench;
        private int overheadPress;
        private int barbellRow;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withBodyWeight(double bodyWeight) {
            this.bodyWeight = bodyWeight;
            return this;
        }

        public Builder withDeadlift(int deadlift) {
            this.deadlift = deadlift;
            return this;
        }

        public Builder withSquat(int squat) {
            this.squat = squat;
            return this;
        }

        public Builder withBench(int bench) {
            this.bench = bench;
            return this;
        }

        public Builder withOverheadPress(int overheadPress) {
            this.overheadPress = overheadPress;
            return this;
        }

        public Builder withBarbellRow(int barbellRow) {
            this.barbellRow = barbellRow;
            return this;
        }

        public UserModel build() {
            return new UserModel(id, userName, bodyWeight, deadlift, squat,
                    bench, overheadPress, barbellRow);
        }
    }
}
