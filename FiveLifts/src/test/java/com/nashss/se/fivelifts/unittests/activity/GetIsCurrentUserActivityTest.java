package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.GetIsCurrentUserActivity;
import com.nashss.se.fivelifts.activity.requests.GetIsCurrentUserRequest;
import com.nashss.se.fivelifts.activity.results.GetIsCurrentUserResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetIsCurrentUserActivityTest {
    @Mock
    private UserDao userDao;

    private GetIsCurrentUserActivity getIsCurrentUserActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getIsCurrentUserActivity = new GetIsCurrentUserActivity(userDao);
    }

    @Test
    public void handleRequest_withCurrentUserEmail_returnsTrue() {
        // GIVEN
        String currentUserEmail = "true@email.com";

        User user = new User();
        user.setEmail(currentUserEmail);
        user.setName("True North");

        when(userDao.getUser(currentUserEmail)).thenReturn(user);

        GetIsCurrentUserRequest request = GetIsCurrentUserRequest.builder()
                .withEmail(currentUserEmail)
                .build();

        // WHEN
        GetIsCurrentUserResult result = getIsCurrentUserActivity.handleRequest(request);

        // THEN
        assertTrue(result.getIsCurrentUser());
    }

    @Test
    public void handleRequest_withNewUserEmail_returnsFalse() {
        // GIVEN
        String newUserEmail = "false@email.com";

        when(userDao.getUser(newUserEmail)).thenThrow(new UserNotFoundException());

        GetIsCurrentUserRequest request = GetIsCurrentUserRequest.builder()
                .withEmail(newUserEmail)
                .build();

        // WHEN
        GetIsCurrentUserResult result = getIsCurrentUserActivity.handleRequest(request);

        // THEN
        assertFalse(result.getIsCurrentUser());
    }
}
