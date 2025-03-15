package com.ziemowit.ts.trivia.app

import android.app.Application
import com.ziemowit.ts.trivia.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class TriviaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object : DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    val prefix = if (BuildConfig.DEBUG) "ZZZ " else ""
                    super.log(priority, tag, "$prefix$message", t)
                }
            })
        }
    }
}
