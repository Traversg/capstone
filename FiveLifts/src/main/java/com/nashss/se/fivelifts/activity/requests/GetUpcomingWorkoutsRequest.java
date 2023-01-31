package com.nashss.se.fivelifts.activity.requests;

/**
 * Implementation of the GetUpcomingWorkoutsRequest for the FiveLifts' GetUpcomingWorkouts API.
 */
public class GetUpcomingWorkoutsRequest {
    private final String email;

    private GetUpcomingWorkoutsRequest(String email) {
        this.email = email;
    }

    public String getUserid() {
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
