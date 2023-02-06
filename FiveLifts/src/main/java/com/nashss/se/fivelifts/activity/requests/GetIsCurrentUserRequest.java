package com.nashss.se.fivelifts.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the GetUserRequest for the FiveLifts' GetUser API.
 */
@JsonDeserialize(builder = GetIsCurrentUserRequest.Builder.class)
public class GetIsCurrentUserRequest {
    private final String email;

    private GetIsCurrentUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
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

        public GetIsCurrentUserRequest build() {
            return new GetIsCurrentUserRequest(email);
        }
    }
}
