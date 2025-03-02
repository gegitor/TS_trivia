package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitRoute
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val routeNavigator: RouteNavigator) :
    ParentViewModel(routeNavigator) {

    init {
        Timber.d("HomeViewModel init")
    }

    private val email = mutableStateOf("home@email")

    internal val interactions = HomeScreenInteractions(
        onBackClicked = ::onBack,
        onNavigateToQuizInit = ::onNavigateToQuizInit,
    )

    internal val state = HomeState(
        email = email,
    )

    private fun onNavigateToQuizInit() {
        Timber.d("onNavigateToQuizInit")
        navigateToRoute(QuizInitRoute.route)
    }
}


