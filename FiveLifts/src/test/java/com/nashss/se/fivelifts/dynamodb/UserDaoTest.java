package com.nashss.se.fivelifts.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        userDao = new UserDao(dynamoDBMapper);
    }

    @Test
    public void getUser_withUserId_callsMapperWithPartitionKey() {
        // GIVEN
        String id = "id";
        when(dynamoDBMapper.load(User.class, id)).thenReturn(new User());

        // WHEN
        User user = userDao.getUser(id);

        // THEN
        assertNotNull(user);
        verify(dynamoDBMapper).load(User.class, id);
    }

    @Test
    public void getUser_userIdNotFound_throwsUserNotFoundException() {
        // GIVEN
        String nonexistentId = "nonexistentId";
        when(dynamoDBMapper.load(User.class, nonexistentId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(UserNotFoundException.class, () -> userDao.getUser(nonexistentId));
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
