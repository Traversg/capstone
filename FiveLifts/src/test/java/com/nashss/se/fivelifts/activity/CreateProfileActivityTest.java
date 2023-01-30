package com.nashss.se.fivelifts.activity;

import com.nashss.se.fivelifts.activity.requests.CreateProfileRequest;
import com.nashss.se.fivelifts.activity.results.CreateProfileResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateProfileActivityTest {
    @Mock
    private UserDao userDao;

    private CreateProfileActivity createProfileActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createProfileActivity = new CreateProfileActivity(userDao);
    }

    @Test
    public void handleRequest_withAllData_createsAndSavesProfileWithData() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedEmail = "expectedEmail";
        double expectedBodyWeight = 175.0;
        int expectedDeadlift = 300;
        int expectedSquat = 250;
        int expectedBench = 200;
        int expectedBarbellRow = 175;
        int expectedOHP = 125;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .withName(expectedName)
                .withEmail(expectedEmail)
                .withBodyWeight(expectedBodyWeight)
                .withDeadlift(expectedDeadlift)
                .withSquat(expectedSquat)
                .withBench(expectedBench)
                .withBarbellRow(expectedBarbellRow)
                .withOverheadPress(expectedOHP)
                .build();

        // WHEN
        CreateProfileResult result = createProfileActivity.handleRequest(request);

        // THEN
        verify(userDao).saveUser(any(User.class));

        assertNotNull(result.getUser().getId());
        assertEquals(expectedName, result.getUser().getName());
        assertEquals(expectedEmail, result.getUser().getEmail());
        assertEquals(expectedBodyWeight, result.getUser().getBodyWeight());
        assertEquals(expectedDeadlift, result.getUser().getDeadlift());
        assertEquals(expectedSquat, result.getUser().getSquat());
        assertEquals(expectedBench, result.getUser().getBench());
        assertEquals(expectedBarbellRow, result.getUser().getBarbellRow());
        assertEquals(expectedOHP, result.getUser().getOverheadPress());
    }


}
