package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.dynamodb.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public CreateProfileActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by persisting a new user
     * with the provided user name, body weight, and lift data from the request.
     * <p>
     * It then returns the newly created user profile.
     * <p>
     * If the provided user name has invalid characters or body weight and lift numbers are too large, throws an
     * InvalidAttributeValueException
     *
     * @param createProfileRequest request object containing the user name, body weight, and lift data
     *                              associated with it
     * @return createProfileResult result object containing the API defined {@link UserModel}
     */
}
