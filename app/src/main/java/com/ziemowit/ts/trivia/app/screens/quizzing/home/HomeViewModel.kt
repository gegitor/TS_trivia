package com.ziemowit.ts.trivia.app.screens.quizzing.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz_init.QuizInitRoute
import com.ziemowit.ts.trivia.domain.repository.PreferencesRepository
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
        Timber.d("HomeViewModel init, name from args: ${homeArgs.userName}")
        Timber.d("HomeViewModel init, name from prefs: ${userName.value}")
//        userName.value = homeArgs.userName
    }

    internal val state = HomeState(
        userName = userName,
        continueQuiz = continueQuiz,
        recentActivities = recentActivities
    )

    internal val interactions = HomeScreenInteractions(
        onBackClicked = ::onBack,
        onContinueQuizClick = ::onContinueQuizClick,
        onQuickPlayClick = ::onQuickPlayClick,
        onCreateCustomQuizClick = ::onCreateCustomQuizClick,
        onChallengeAcceptClick = ::onChallengeAcceptClick,
        onShareClick = ::onShareClick,
        dismissError = ::dismissError,
    )

    private fun onContinueQuizClick() {
        //TODO
        Timber.d("onContinueQuizClick")
    }

    private fun onQuickPlayClick() {
        Timber.d("onQuickPlayClick")
        navigateToRoute(QuizInitRoute.route)
    }

    private fun onCreateCustomQuizClick() {
        //TODO
        Timber.d("onCreateCustomQuizClick")
    }

    private fun onChallengeAcceptClick(id: String) {
        //TODO
        Timber.d("onChallengeAcceptClick: $id")
    }

    private fun onShareClick(id: String) {
        //TODO
        Timber.d("onShareClick: $id")
    }

    private fun dismissError() {
        //TODO
        Timber.d("dismissError")
    }
}
