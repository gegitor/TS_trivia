package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitRoute
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(routeNavigator: RouteNavigator) :
    ParentViewModel(routeNavigator) {

    init {
        Timber.d("HomeViewModel init")
    }

    internal val interactions = HomeScreenInteractions(
        onBackClicked = ::onBack,
        onNavigateToQuizInit = ::onNavigateToQuizInit,
        onContinueQuizClick = ::onContinueQuizClick,
        onQuickPlayAddClick = ::onQuickPlayAddClick,
        onCreateCustomQuizClick = ::onCreateCustomQuizClick,
        onChallengeAcceptClick = ::onChallengeAcceptClick,
        onShareClick = ::onShareClick,
        dismissError = ::dismissError,
    )

    private val userName = mutableStateOf("")
    private val continueQuiz = mutableStateOf(null)
    private val recentActivities: MutableState<List<HomeState.ActivityItem>> = mutableStateOf(
        emptyList()
    )

    internal val state = HomeState(
        userName = userName,
        continueQuiz = continueQuiz,
        recentActivities = recentActivities
    )

    private fun onNavigateToQuizInit() {
        Timber.d("onNavigateToQuizInit")
        navigateToRoute(QuizInitRoute.route)
    }

    private fun onContinueQuizClick() {
        Timber.d("onContinueQuizClick")
    }

    private fun onQuickPlayAddClick() {
        Timber.d("onQuickPlayAddClick")
    }

    private fun onCreateCustomQuizClick() {
        Timber.d("onCreateCustomQuizClick")
    }

    private fun onChallengeAcceptClick(id: String) {
        Timber.d("onChallengeAcceptClick: $id")
    }

    private fun onShareClick(id: String) {
        Timber.d("onShareClick: $id")
    }

    private fun dismissError() {
        Timber.d("dismissError")
    }
}
