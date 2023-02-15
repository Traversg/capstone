package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the GetMostRecentWorkoutRequest
 * for the FiveLifts' GetMostRecentWorkout API.
 */
@JsonDeserialize(builder = GetMostRecentWorkoutRequest.Builder.class)
public class GetMostRecentWorkoutRequest {
    private final String email;

    private GetMostRecentWorkoutRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetMostRecentWorkoutRequest{" +
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

        public GetMostRecentWorkoutRequest build() {
            return new GetMostRecentWorkoutRequest(email);
        }
    }
}
