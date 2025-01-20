package com.ziemowit.ts.trivia.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.ziemowit.ts.trivia.audio.SoundRepository
import com.ziemowit.ts.trivia.audio.SoundRepositoryImpl
import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.QuestionRepositoryImpl
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
    fun geQuestionRepository(context: Context): QuestionRepository = QuestionRepositoryImpl(context)
}