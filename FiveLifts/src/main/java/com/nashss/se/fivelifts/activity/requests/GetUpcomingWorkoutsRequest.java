package com.nashss.se.fivelifts.activity.requests;

/**
 * Implementation of the GetUpcomingWorkoutsRequest for the FiveLifts' GetUpcomingWorkouts API.
 */
public class GetUpcomingWorkoutsRequest {
    private final String userid;

    private GetUpcomingWorkoutsRequest(String userId) {
        this.userid = userId;
    }

    public String getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "GetUpcomingWorkoutsRequest{" +
                "userid='" + userid + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetUpcomingWorkoutsRequest build() {
            return new GetUpcomingWorkoutsRequest(userId);
        }
    }
}
