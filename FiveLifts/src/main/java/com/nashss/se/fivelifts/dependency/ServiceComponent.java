package com.nashss.se.fivelifts.dependency;
import com.nashss.se.fivelifts.activity.AddWorkoutActivity;
import com.nashss.se.fivelifts.activity.CreateProfileActivity;
import com.nashss.se.fivelifts.activity.GetIsCurrentUserActivity;
import com.nashss.se.fivelifts.activity.GetUpcomingWorkoutsActivity;
import com.nashss.se.fivelifts.activity.GetWorkoutHistoryActivity;

import com.nashss.se.fivelifts.metrics.MetricsPublisher;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the FiveLifts Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreateProfileActivity
     */
    CreateProfileActivity provideCreateProfileActivity();

    /**
     * Provides the relevant activity.
     * @return GetUpcomingWorkoutsActivity
     */
    GetUpcomingWorkoutsActivity provideGetUpcomingWorkoutsActivity();

    /**
     * Provides the relevant activity.
     * @return GetIsCurrentUserActivity
     */
    GetIsCurrentUserActivity provideGetIsCurrentUserActivity();

    /**
     * Provides the relevant activity.
     * @return AddWorkoutActivity
     */
    AddWorkoutActivity provideAddWorkoutActivity();

    /**
     * Provides the relevant activity.
     * @return GetWorkoutHistoryActivity
     */
    GetWorkoutHistoryActivity provideGetWorkoutHistoryActivity();
}
