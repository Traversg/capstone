package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.CreateProfileRequest;
import com.nashss.se.fivelifts.activity.results.CreateProfileResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.models.UserModel;
import com.nashss.se.fivelifts.utils.FiveLiftsServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateProfileActivity for the FiveLifts' CreateProfile API.
 * <p>
 * This API allows the user to create a new user profile with all of their lift information.
 */
public class CreateProfileActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new CreateProfileActivity object.
     *
     * @param userDao UserDap to access the playlists table.
     */
    @Inject
    public CreateProfileActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by persisting a new user
     * with the provided user name, body weight, and lift data from the request.
     * <p>
     * It then returns the newly created user profile.
     * <p>
     * If the provided body weight and lift numbers are too large, throws an
     * InvalidAttributeValueException
     *
     * If any provided weight fields are empty, throws an InvalidAttributeValueException
     * @param createProfileRequest request object containing the user name, body weight, and lift data
     *                              associated with it
     * @return createProfileResult result object containing the API defined {@link UserModel}
     */
    public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest) {
        log.info("Received CreateProfileRequest {}", createProfileRequest);

        User newUser = new User();
        newUser.setId(FiveLiftsServiceUtils.generateUserId());
        newUser.setName(createProfileRequest.getName());
        newUser.setEmail(createProfileRequest.getEmail());
        newUser.setBodyWeight(createProfileRequest.getBodyWeight());
        newUser.setBarbellRow(createProfileRequest.getBarbellRow());
        newUser.setDeadlift(createProfileRequest.getDeadlift());
        newUser.setBench(createProfileRequest.getBench());
        newUser.setOverheadPress(createProfileRequest.getOverheadPress());
        newUser.setSquat(createProfileRequest.getSquat());

        userDao.saveUser(newUser);

        UserModel userModel = new ModelConverter().toUserModel(newUser);
        return CreateProfileResult.builder()
                .withUser(userModel)
                .build();
    }
}
