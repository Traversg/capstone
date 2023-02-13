package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.DeleteUserProfileAndWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.DeleteUserProfileAndWorkoutHistoryResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.exceptions.UserNotFoundException;
import com.nashss.se.fivelifts.exceptions.WorkoutNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the DeleteUserProfileAndWorkoutHistoryActivity
 * for the FiveLifts' DeleteUserProfileAndWorkoutHistory API.
 * <p>
 * This API checks deletes a user from the user profile table and
 * deletes all of their workouts from the workout table
 */
public class DeleteUserProfileAndWorkoutHistoryActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new DeleteUserProfileAndWorkoutHistoryActivity object.
     *
     * @param userDao UserDao to access the users table.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public DeleteUserProfileAndWorkoutHistoryActivity(UserDao userDao,
                                                      WorkoutDao workoutDao) {
        this.userDao = userDao;
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by deleting the user
     * and their corresponding workouts from the user table and workout table.
     * <p>
     * It then returns true and the user's email when deleted
     * <p>
     *
     * @param deleteUserProfileAndWorkoutHistoryRequest request object
     * containing the user email
     * @return deleteUserProfileAndWorkoutHistory result object containing the API defined
     */
    public DeleteUserProfileAndWorkoutHistoryResult handleRequest(
            final DeleteUserProfileAndWorkoutHistoryRequest
                    deleteUserProfileAndWorkoutHistoryRequest
    ) {
        log.info("Received DeleteUserProfileAndWorkoutHistoryRequest {}",
                deleteUserProfileAndWorkoutHistoryRequest);

        String userEmail = deleteUserProfileAndWorkoutHistoryRequest.getEmail();

        User user = new User();
        user.setEmail(userEmail);
        userDao.deleteUser(user);

        boolean userDeleted = false;
        try {
            userDao.getUser(userEmail);
        } catch (UserNotFoundException e) {
            userDeleted = true;
        }

        List<Workout> workoutHistory = workoutDao.getWorkoutHistory(userEmail);
        for (Workout workout : workoutHistory) {
            Workout workoutToDelete = new Workout();
            workoutToDelete.setEmail(userEmail);
            workoutToDelete.setWorkoutDate(workout.getWorkoutDate());
            workoutDao.deleteWorkout(workoutToDelete);
        }

        boolean workoutHistoryDeleted = false;
        try {
            workoutDao.getWorkoutHistory(userEmail);
        } catch (WorkoutNotFoundException e) {
            workoutHistoryDeleted = true;
        }

        if (userDeleted && workoutHistoryDeleted) {
            return DeleteUserProfileAndWorkoutHistoryResult.builder()
                    .withEmail(userEmail)
                    .withIsDeleted(true)
                    .build();
        }

        return DeleteUserProfileAndWorkoutHistoryResult.builder()
                .withEmail(userEmail)
                .withIsDeleted(false)
                .build();
    }
}
