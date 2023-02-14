package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the DeleteUserProfileAndWorkoutHistoryRequest
 * for the FiveLifts' DeleteUserProfileAndWorkoutHistory API.
 */

@JsonDeserialize(builder = DeleteUserProfileAndWorkoutHistoryRequest.Builder.class)
public class DeleteUserProfileAndWorkoutHistoryRequest {
    private final String email;

    private DeleteUserProfileAndWorkoutHistoryRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "DeleteUserProfileAndWorkoutHistoryRequest{" +
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

        public DeleteUserProfileAndWorkoutHistoryRequest build() {
            return new DeleteUserProfileAndWorkoutHistoryRequest(email);
        }
    }
}
