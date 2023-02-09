package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the GetUpcomingWorkoutsRequest for the FiveLifts' GetUpcomingWorkouts API.
 */
@JsonDeserialize(builder = GetWorkoutHistoryRequest.Builder.class)
public class GetWorkoutHistoryRequest {
    private final String email;

    private GetWorkoutHistoryRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetWorkoutHistoryRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public GetWorkoutHistoryRequest build() {
            return new GetWorkoutHistoryRequest(email);
        }
    }
}
