package com.nashss.se.fivelifts.unittests.activity;

import com.nashss.se.fivelifts.activity.GetWorkoutHistoryActivity;
import com.nashss.se.fivelifts.activity.requests.GetWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.GetWorkoutHistoryResult;
import com.nashss.se.fivelifts.dynamodb.WorkoutDao;
import com.nashss.se.fivelifts.dynamodb.models.Workout;
import com.nashss.se.fivelifts.enums.WorkoutType;
import com.nashss.se.fivelifts.models.WorkoutModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetWorkoutHistoryActivityTest {
    @Mock
    private WorkoutDao workoutDao;

    private GetWorkoutHistoryActivity getWorkoutHistoryActivity;

    @BeforeEach
    void setUp(){
        openMocks(this);
        getWorkoutHistoryActivity = new GetWorkoutHistoryActivity(workoutDao);
    }

    @Test
    public void handleRequest_withEmail_returnsWorkoutHistoryModel() {
        // GIVEN
        String testEmail = "test@email.com";

        Workout mostRecent = new Workout();
        mostRecent.setWorkoutDate(LocalDate.of(2023, Month.FEBRUARY, 02));
        mostRecent.setWorkoutType(WorkoutType.WORKOUT_A);
        mostRecent.setSquatWeight(230);
        mostRecent.setBenchPressWeight(180);
        mostRecent.setBarbellRowWeight(155);

        Workout old = new Workout();
        old.setWorkoutDate(LocalDate.of(2023, Month.JANUARY, 31));
        old.setWorkoutType(WorkoutType.WORKOUT_B);
        old.setSquatWeight(225);
        old.setOverheadPressWeight(130);
        old.setDeadliftWeight(285);

        Workout oldest = new Workout();
        oldest.setWorkoutDate(LocalDate.of(2023, Month.JANUARY, 28));
        oldest.setWorkoutType(WorkoutType.WORKOUT_A);
        oldest.setSquatWeight(240);
        oldest.setBenchPressWeight(175);
        oldest.setBarbellRowWeight(150);

        List<Workout> workoutHistory = new ArrayList<>();

        workoutHistory.add(mostRecent);
        workoutHistory.add(old);
        workoutHistory.add(oldest);

        when(workoutDao.getWorkoutHistory(testEmail)).thenReturn(workoutHistory);

        GetWorkoutHistoryRequest request = GetWorkoutHistoryRequest.builder()
                .withEmail(testEmail)
                .build();

        // WHEN
        GetWorkoutHistoryResult result = getWorkoutHistoryActivity.handleRequest(request);
        List<WorkoutModel> resultModels = result.getWorkoutHistory();

        // THEN
        assertEquals(workoutHistory.get(0).getWorkoutDate(), resultModels.get(0).getWorkoutDate());
        assertEquals(workoutHistory.get(0).getWorkoutType(), resultModels.get(0).getWorkoutType());
        assertEquals(workoutHistory.get(0).getSquatWeight(), resultModels.get(0).getSquatWeight());
        assertEquals(workoutHistory.get(0).getBenchPressWeight(), resultModels.get(0).getBenchPressWeight());
        assertEquals(workoutHistory.get(0).getBarbellRowWeight(), resultModels.get(0).getBarbellRowWeight());
        assertEquals(workoutHistory.get(1).getWorkoutDate(), resultModels.get(1).getWorkoutDate());
        assertEquals(workoutHistory.get(1).getWorkoutType(), resultModels.get(1).getWorkoutType());
        assertEquals(workoutHistory.get(1).getSquatWeight(), resultModels.get(1).getSquatWeight());
        assertEquals(workoutHistory.get(1).getOverheadPressWeight(), resultModels.get(1).getOverheadPressWeight());
        assertEquals(workoutHistory.get(1).getDeadliftWeight(), resultModels.get(1).getDeadliftWeight());
        assertEquals(workoutHistory.get(2).getWorkoutDate(), resultModels.get(2).getWorkoutDate());
        assertEquals(workoutHistory.get(2).getWorkoutType(), resultModels.get(2).getWorkoutType());
        assertEquals(workoutHistory.get(2).getSquatWeight(), resultModels.get(2).getSquatWeight());
        assertEquals(workoutHistory.get(2).getBenchPressWeight(), resultModels.get(2).getBenchPressWeight());
        assertEquals(workoutHistory.get(2).getBarbellRowWeight(), resultModels.get(2).getBarbellRowWeight());
    }
}
