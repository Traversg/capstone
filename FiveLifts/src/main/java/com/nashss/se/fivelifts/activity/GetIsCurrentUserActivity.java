package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.GetIsCurrentUserRequest;
import com.nashss.se.fivelifts.activity.results.GetIsCurrentUserResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetIsCurrentUserActivity for the FiveLifts' GetIsCurrentUser API.
 * <p>
 * This API checks to see if the user is a new or current user.
 */
public class GetIsCurrentUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new GetIsCurrentUserActivity object.
     *
     * @param userDao UserDao to access the users table.
     */
    @Inject
    public GetIsCurrentUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by retrieving the user from the database
     * <p>
     * If the user is found, true is returned. Otherwise, false.
     * <p>
     *
     * @param getIsCurrentUserRequest request object containing the email
     * @return getIsCurrentUserActivity result object containing the API defined {@link boolean}
     */
    public GetIsCurrentUserResult handleRequest(final GetIsCurrentUserRequest getIsCurrentUserRequest) {
        log.info("Received GetIsCurrentUserRequest {}", getIsCurrentUserRequest);

        try {
            User user = userDao.getUser(getIsCurrentUserRequest.getEmail());
        } catch (UserNotFoundException e) {
            return GetIsCurrentUserResult.builder()
                    .withIsCurrentUser(false)
                    .build();
        }

        return GetIsCurrentUserResult.builder()
                .withIsCurrentUser(true)
                .build();
    }
}
