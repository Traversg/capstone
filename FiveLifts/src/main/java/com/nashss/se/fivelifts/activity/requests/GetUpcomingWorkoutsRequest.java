package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the GetUpcomingWorkoutsRequest for the FiveLifts' GetUpcomingWorkouts API.
 */
@JsonDeserialize(builder = GetUpcomingWorkoutsRequest.Builder.class)
public class GetUpcomingWorkoutsRequest {
    private final String email;

    private GetUpcomingWorkoutsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetUpcomingWorkoutsRequest{" +
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

        public GetUpcomingWorkoutsRequest build() {
            return new GetUpcomingWorkoutsRequest(email);
        }
    }
}
