package com.nashss.se.fivelifts.activity.results;

/**
 * Implementation of the GetUserResult for the FiveLifts' GetUser API.
 */
public class GetIsCurrentUserResult {
    private final boolean isCurrentUser;

    private GetIsCurrentUserResult(boolean isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
    }

    public boolean getIsCurrentUser() {
        return isCurrentUser;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean isCurrentUser;

        public Builder withIsCurrentUser(boolean isCurrentUser) {
            this.isCurrentUser = isCurrentUser;
            return this;
        }

        public GetIsCurrentUserResult build() {
            return new GetIsCurrentUserResult(isCurrentUser);
        }
    }
}
