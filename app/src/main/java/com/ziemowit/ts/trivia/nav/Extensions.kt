package com.ziemowit.ts.trivia.nav

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.ziemowit.ts.trivia.app.screens.quizzing.main.Screen
import timber.log.Timber


/**
 * Created by Ziemowit Pazderski on 3/11/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */
fun NavHostController.navigateToScreen(screen: Screen) {
    navigateToScreen(screen.route, screen.route)
}

fun NavHostController.navigateToScreen(screenName: String, route: String) {
    Timber.d("navigateToScreen route: $route screenName: $screenName")
    navigate(
        route = route,
        navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(screenName, inclusive = false, saveState = true)
            .setRestoreState(true)
            .build()
    )
}