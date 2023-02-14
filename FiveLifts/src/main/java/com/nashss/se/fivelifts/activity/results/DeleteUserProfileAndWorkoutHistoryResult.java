package com.nashss.se.fivelifts.activity.results;

/**
 * Implementation of the DeleteUserProfileAndWorkoutHistoryResult
 * for the FiveLifts' DeleteUserProfileAndWorkoutHistory API.
 */
public class DeleteUserProfileAndWorkoutHistoryResult {
    private final String email;
    private final boolean isDeleted;

    private DeleteUserProfileAndWorkoutHistoryResult(String email,
                                                     boolean isDeleted) {
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private boolean isDeleted;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public DeleteUserProfileAndWorkoutHistoryResult build() {
            return new DeleteUserProfileAndWorkoutHistoryResult(email, isDeleted);
        }
    }
}
