package com.nashss.se.fivelifts.dependency;
import com.nashss.se.fivelifts.activity.CreateProfileActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreateProfileActivity
     */
    CreateProfileActivity provideCreateProfileActivity();
}
