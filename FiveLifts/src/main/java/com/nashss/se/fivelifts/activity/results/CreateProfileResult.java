package com.nashss.se.fivelifts.activity.results;

import com.nashss.se.fivelifts.models.UserModel;

public class CreateProfileResult {

    private final UserModel user;

    private CreateProfileResult(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CreateProfileResult{" +
                "user=" + user +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserModel user;

        public Builder withUser(UserModel user) {
            this.user = user;
            return this;
        }

        public CreateProfileResult build() {
            return new CreateProfileResult(user);
        }
    }
}
