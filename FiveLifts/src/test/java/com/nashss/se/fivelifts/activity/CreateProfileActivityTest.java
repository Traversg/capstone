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

        assertNotNull(result.getProfile().getId());
        assertEquals(expectedName, result.getProfile().getName());
        assertEquals(expectedEmail, result.getProfile().getEmail());
        assertEquals(expectedBodyWeight, result.getProfile().getBodyWeight());
        assertEquals(expectedDeadlift, result.getProfile().getDeadlift());
        assertEquals(expectedSquat, result.getProfile().getSquat());
        assertEquals(expectedBench, result.getProfile().getBench());
        assertEquals(expectedBarbellRow, result.getProfile().getBarbellRow());
        assertEquals(expectedOHP, result.getProfile().getOverheadPress());
    }


}
