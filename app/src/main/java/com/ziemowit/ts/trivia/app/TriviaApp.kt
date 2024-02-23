package com.ziemowit.ts.trivia.app

import android.app.Application
import com.ziemowit.ts.trivia.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.*


@HiltAndroidApp
class TriviaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}