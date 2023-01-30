package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.UserModel;

/**
 * Implementation of the CreateProfileResult for the FiveLifts' CreateProfile API.
 */
public class CreateProfileResult {

    private final UserModel profile;

    private CreateProfileResult(UserModel profile) {
        this.profile = profile;
    }

    public UserModel getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "CreateProfileResult{" +
                "profile=" + profile +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserModel profile;

        public Builder withProfile(UserModel profile) {
            this.profile = profile;
            return this;
        }

        public CreateProfileResult build() {
            return new CreateProfileResult(profile);
        }
    }
}
