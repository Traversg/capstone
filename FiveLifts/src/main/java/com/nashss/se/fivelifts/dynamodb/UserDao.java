package com.nashss.se.fivelifts.dynamodb;

import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a user using {@link User} to represent the model in DynamoDB.
 */
@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a UserDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the users table
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a User by email.
     *
     * If not found, throws UserNotFoundException
     *
     * @param email The email to look up
     * @return The corresponding User if found
     */
    public User getUser(String email) {
        User user = dynamoDbMapper.load(User.class, email);
        if (null == user) {
            throw new UserNotFoundException(String.format(
                    "Could not find User with email '%s'", email
            ));
        }
        return user;
    }

    /**
     * Saves (creates or updates) the given user.
     *
     * @param user The user to save
     */
    public void saveUser(User user) {
        dynamoDbMapper.save(user);
    }

    /**
     * Delete the given user.
     *
     * @param user The user to delete
     */
    public void deleteUser(User user) {
        dynamoDbMapper.delete(user);
    }
}
