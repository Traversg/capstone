package com.nashss.se.fivelifts.converters;

import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.models.UserModel;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     *
     * @param user the user to convert
     * @return the converted user
     */
    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withUserName(user.getUserName())
                .withBodyWeight(user.getBodyWeight())
                .withBarbellRow(user.getBarbellRow())
                .withBench(user.getBench())
                .withDeadlift(user.getDeadlift())
                .withOverheadPress(user.getOverheadPress())
                .withSquat(user.getSquat())
                .build();
    }
}
