package com.ziemowit.ts.trivia.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.ziemowit.ts.trivia.domain.repository.LeaderRepository
import com.ziemowit.ts.trivia.domain.repository.LeaderRepositoryImpl
import com.ziemowit.ts.trivia.domain.repository.PreferencesRepository
import com.ziemowit.ts.trivia.domain.repository.PreferencesRepositoryImpl
import com.ziemowit.ts.trivia.domain.repository.QuestionRepository
import com.ziemowit.ts.trivia.domain.repository.QuestionRepositoryImpl
import com.ziemowit.ts.trivia.domain.repository.SoundRepository
import com.ziemowit.ts.trivia.domain.repository.SoundRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


const val SHARED_PREFS_NAME = "TS_TRIVIA_SHARED_PREFS"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getContext(application: Application): Context = application

    @Provides
    @Singleton
    fun getResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun getSharedPrefs(application: Application): SharedPreferences =
        application.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getSoundRepository(context: Context): SoundRepository = SoundRepositoryImpl(context)

    @Provides
    @Singleton
    fun getQuestionRepository(context: Context, dispatcherProvider: DispatcherProvider): QuestionRepository = QuestionRepositoryImpl(context, dispatcherProvider)

    @Provides
    @Singleton
    fun getLeaderRepository(context: Context): LeaderRepository = LeaderRepositoryImpl(context)

    @Provides
    @Singleton
    fun getPreferencesRepository(sharedPreferences: SharedPreferences): PreferencesRepository = PreferencesRepositoryImpl(sharedPreferences)
}