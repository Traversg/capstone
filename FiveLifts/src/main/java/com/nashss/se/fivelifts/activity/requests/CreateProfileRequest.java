package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the CreateProfileRequest for the FiveLifts' CreateProfile API.
 */
@JsonDeserialize(builder = CreateProfileRequest.Builder.class)
public class CreateProfileRequest {
    private final String name;
    private final String email;
    private final double bodyWeight;
    private final int deadlift;
    private final int squat;
    private final int bench;
    private final int overheadPress;
    private final int barbellRow;

    private CreateProfileRequest(String name, String email, double bodyWeight, int deadlift, int squat,
                                 int bench, int overheadPress, int barbellRow) {
        this.name = name;
        this.email = email;
        this.bodyWeight = bodyWeight;
        this.deadlift = deadlift;
        this.squat = squat;
        this.bench = bench;
        this.overheadPress = overheadPress;
        this.barbellRow = barbellRow;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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
    public String toString() {
        return "CreateProfileRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bodyWeight=" + bodyWeight + '\'' +
                ", deadlift=" + deadlift + '\'' +
                ", squat=" + squat + '\'' +
                ", bench=" + bench + '\'' +
                ", overheadPress=" + overheadPress + '\'' +
                ", barbellRow=" + barbellRow +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String email;
        private double bodyWeight;
        private int deadlift;
        private int squat;
        private int bench;
        private int overheadPress;
        private int barbellRow;


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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

        public Builder withOverheadPres(int overheadPress) {
            this.overheadPress = overheadPress;
            return this;
        }

        public Builder withBarbellRow(int barbellRow) {
            this.barbellRow = barbellRow;
            return this;
        }

        public CreateProfileRequest build() {
            return new CreateProfileRequest(name, email, bodyWeight, deadlift, squat,
                    bench, overheadPress, barbellRow);
        }

    }
}
