package com.nashss.se.fivelifts.unittests.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDao(dynamoDBMapper);
    }

    @Test
    public void getUser_withUserId_callsMapperWithPartitionKey() {
        // GIVEN
        String email = "name@email.com";
        when(dynamoDBMapper.load(User.class, email)).thenReturn(new User());

        // WHEN
        User user = userDao.getUser(email);

        // THEN
        assertNotNull(user);
        verify(dynamoDBMapper).load(User.class, email);
    }

    @Test
    public void getUser_userIdNotFound_throwsUserNotFoundException() {
        // GIVEN
        String nonexistentEmail = "nonExisistentEmail@email.com";
        when(dynamoDBMapper.load(User.class, nonexistentEmail)).thenReturn(null);

        // WHEN + THEN
        assertThrows(UserNotFoundException.class, () -> userDao.getUser(nonexistentEmail));
    }

    @Test
    public void saveUser_callsMapperWithUser() {
        // GIVEN
        User user = new User();

        // WHEN
        userDao.saveUser(user);

        // THEN
        verify(dynamoDBMapper).save(user);
    }
}
