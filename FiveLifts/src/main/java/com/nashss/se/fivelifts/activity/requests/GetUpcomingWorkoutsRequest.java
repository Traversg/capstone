package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the GetUpcomingWorkoutsRequest for the FiveLifts' GetUpcomingWorkouts API.
 */
@JsonDeserialize(builder = GetUpcomingWorkoutsRequest.Builder.class)
public class GetUpcomingWorkoutsRequest {
    private final String email;
    private final String currentWorkout;

    private GetUpcomingWorkoutsRequest(String email, String currentWorkout) {
        this.email = email;
        this.currentWorkout = currentWorkout;
    }

    public String getEmail() {
        return email;
    }

    public String isCurrentWorkout() {
        return currentWorkout;
    }

    @Override
    public String toString() {
        return "GetUpcomingWorkoutsRequest{" +
                "email='" + email + '\'' +
                ", currentWorkout=" + currentWorkout +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String currentWorkout;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withCurrentWorkout(String currentWorkout) {
            this.currentWorkout = currentWorkout;
            return this;
        }

        public GetUpcomingWorkoutsRequest build() {
            return new GetUpcomingWorkoutsRequest(email, currentWorkout);
        }
    }
}
