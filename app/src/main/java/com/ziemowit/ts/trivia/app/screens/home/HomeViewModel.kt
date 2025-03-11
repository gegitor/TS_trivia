package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitRoute
import com.ziemowit.ts.trivia.data.PreferencesRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
    preferencesRepository: PreferencesRepository
) :
    ParentViewModel(routeNavigator) {

    private val homeArgs = HomeArgs(savedStateHandle)

//    private val userName = mutableStateOf(preferencesRepository.getUserName() ?: "")
    private val userName = mutableStateOf(preferencesRepository.getUserName() ?: "")
    private val continueQuiz = mutableStateOf(null)
    private val recentActivities: MutableState<List<HomeState.ActivityItem>> = mutableStateOf(
        emptyList()
    )

    init {
        Timber.d("HomeViewModel init, name: ${homeArgs.userName}")
//        userName.value = homeArgs.userName
    }

    internal val state = HomeState(
        userName = userName,
        continueQuiz = continueQuiz,
        recentActivities = recentActivities
    )

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
