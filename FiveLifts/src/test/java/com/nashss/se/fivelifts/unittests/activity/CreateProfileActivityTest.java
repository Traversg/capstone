package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.CreateProfileActivity;
import com.nashss.se.fivelifts.activity.requests.CreateProfileRequest;
import com.nashss.se.fivelifts.activity.results.CreateProfileResult;
import com.nashss.se.fivelifts.dynamodb.UserDao;
import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.exceptions.BodyWeightLessThanZeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                .withBenchPress(expectedBench)
                .withBarbellRow(expectedBarbellRow)
                .withOverheadPress(expectedOHP)
                .build();

        // WHEN
        CreateProfileResult result = createProfileActivity.handleRequest(request);

        // THEN
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedName, result.getProfile().getName());
        assertEquals(expectedEmail, result.getProfile().getEmail());
        assertEquals(expectedBodyWeight, result.getProfile().getBodyWeight());
        assertEquals(expectedDeadlift, result.getProfile().getDeadlift());
        assertEquals(expectedSquat, result.getProfile().getSquat());
        assertEquals(expectedBench, result.getProfile().getBenchPress());
        assertEquals(expectedBarbellRow, result.getProfile().getBarbellRow());
        assertEquals(expectedOHP, result.getProfile().getOverheadPress());
    }

    @Test
    public void handleRequest_withBelowMinimumWeights_createsAndSavesProfileWith45lbsforEachLift() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedEmail = "expectedEmail";
        double expectedBodyWeight = 175.0;
        int expectedWeight = 45;
        int deadlift = 25;
        int squat = 25;
        int bench = 20;
        int barbellRow = 20;
        int overheadPress = 15;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .withName(expectedName)
                .withEmail(expectedEmail)
                .withBodyWeight(expectedBodyWeight)
                .withDeadlift(deadlift)
                .withSquat(squat)
                .withBenchPress(bench)
                .withBarbellRow(barbellRow)
                .withOverheadPress(overheadPress)
                .build();

        // WHEN
        CreateProfileResult result = createProfileActivity.handleRequest(request);

        // THEN
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedName, result.getProfile().getName());
        assertEquals(expectedEmail, result.getProfile().getEmail());
        assertEquals(expectedBodyWeight, result.getProfile().getBodyWeight());
        assertEquals(expectedWeight, result.getProfile().getDeadlift());
        assertEquals(expectedWeight, result.getProfile().getSquat());
        assertEquals(expectedWeight, result.getProfile().getBenchPress());
        assertEquals(expectedWeight, result.getProfile().getBarbellRow());
        assertEquals(expectedWeight, result.getProfile().getOverheadPress());
    }

    @Test
    public void handleRequest_withStartingWeightsNotDivisibleBy5_returnsRoundedDownStartingWeights() {
        // GIVEN
        String expectedName = "expectedName";
        String expectedEmail = "expectedEmail";
        double expectedBodyWeight = 175.0;
        int deadlift = 303;
        int squat = 278;
        int bench = 174;
        int barbellRow = 159;
        int overheadPress = 147;

        int expectedDeadlift = 300;
        int expectedSquat = 275;
        int expectedBench = 170;
        int expectedBarbellRow = 155;
        int expectedOverheadPress = 145;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .withName(expectedName)
                .withEmail(expectedEmail)
                .withBodyWeight(expectedBodyWeight)
                .withDeadlift(deadlift)
                .withSquat(squat)
                .withBenchPress(bench)
                .withBarbellRow(barbellRow)
                .withOverheadPress(overheadPress)
                .build();

        // WHEN
        CreateProfileResult result = createProfileActivity.handleRequest(request);

        // THEN
        verify(userDao).saveUser(any(User.class));

        assertEquals(expectedName, result.getProfile().getName());
        assertEquals(expectedEmail, result.getProfile().getEmail());
        assertEquals(expectedBodyWeight, result.getProfile().getBodyWeight());
        assertEquals(expectedDeadlift, result.getProfile().getDeadlift());
        assertEquals(expectedSquat, result.getProfile().getSquat());
        assertEquals(expectedBench, result.getProfile().getBenchPress());
        assertEquals(expectedBarbellRow, result.getProfile().getBarbellRow());
        assertEquals(expectedOverheadPress, result.getProfile().getOverheadPress());
    }

    @Test
    public void handleRequest_withBodyWeightLessThanZero_throwBodyWeightLessThanZeroException() {
        // GIVEN
        String name = "expectedName";
        String email = "expectedEmail";
        double bodyWeight = -1;
        int deadlift = 303;
        int squat = 278;
        int bench = 174;
        int barbellRow = 159;
        int overheadPress = 147;

        CreateProfileRequest request = CreateProfileRequest.builder()
                .withName(name)
                .withEmail(email)
                .withBodyWeight(bodyWeight)
                .withDeadlift(deadlift)
                .withSquat(squat)
                .withBenchPress(bench)
                .withBarbellRow(barbellRow)
                .withOverheadPress(overheadPress)
                .build();

        // WHEN + THEN
        assertThrows(BodyWeightLessThanZeroException.class, () -> createProfileActivity.handleRequest(request));
    }
}
