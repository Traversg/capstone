package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.CreateProfileRequest;
import com.nashss.se.fivelifts.activity.results.CreateProfileResult;
import com.nashss.se.fivelifts.converters.ModelConverter;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.BodyWeightLessThanZeroException;
import com.nashss.se.fivelifts.models.UserModel;

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
     * @param userDao UserDao to access the users table.
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
     *
     * If user enters starting weights below 45lbs they will be set to 45lbs.
     * If user enters starting weights not divisible by 5, weight will be rounded down.
     * @param createProfileRequest request object containing the user name, body weight, and lift data
     *                              associated with it
     * @return createProfileResult result object containing the API defined {@link UserModel}
     */
    public CreateProfileResult handleRequest(final CreateProfileRequest createProfileRequest) {
        log.info("Received CreateProfileRequest {}", createProfileRequest);

        int minimumWeight = 45;
        int startingBarbellRowWeight = createProfileRequest.getBarbellRow();
        int startingDeadliftWeight = createProfileRequest.getDeadlift();
        int startingBenchPressWeight = createProfileRequest.getBenchPress();
        int startingOverheadPressWeight = createProfileRequest.getOverheadPress();
        int startingSquatWeight = createProfileRequest.getSquat();
        double bodyWeight = createProfileRequest.getBodyWeight();

        if (bodyWeight < 0) {
            throw new BodyWeightLessThanZeroException("Body Weight cannot be less than zero.");
        }

        User newUser = new User();
        newUser.setName(createProfileRequest.getName());
        newUser.setEmail(createProfileRequest.getEmail());
        newUser.setBodyWeight(bodyWeight);
        newUser.setBarbellRow(Math.max(roundDown(startingBarbellRowWeight), minimumWeight));
        newUser.setDeadlift(Math.max(roundDown(startingDeadliftWeight), minimumWeight));
        newUser.setBenchPress(Math.max(roundDown(startingBenchPressWeight), minimumWeight));
        newUser.setOverheadPress(Math.max(roundDown(startingOverheadPressWeight), minimumWeight));
        newUser.setSquat(Math.max(roundDown(startingSquatWeight), minimumWeight));

        userDao.saveUser(newUser);

        UserModel userModel = new ModelConverter().toUserModel(newUser);
        return CreateProfileResult.builder()
                .withProfile(userModel)
                .build();
    }
    /**
     * If lift entered is not divisible by 5,
     * rounds down to nearest number divisible by 5.
     * @param lift weight entered
     * @return lift weight
     * */
    public int roundDown(int lift) {
        int remainder = lift % 5;
        if (remainder == 0) {
            return lift;
        } else {
            return lift - remainder;
        }
    }
}
