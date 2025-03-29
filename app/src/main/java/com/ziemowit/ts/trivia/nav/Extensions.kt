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
    Timber.d("navigateToScreen route: ${screen.route}")
    navigate(
        route = screen.route,
        navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(0, inclusive = false, saveState = true)
            .setRestoreState(true)
            .build()
    )
}