package com.nashss.se.fivelifts.dependency;
import com.nashss.se.fivelifts.activity.CreateProfileActivity;

import com.nashss.se.fivelifts.activity.GetUpcomingWorkoutsActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the FiveLifts Service.
 */
@Singleton
@Component(modules = {DaoModule.class})
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
}
