package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty
import com.ziemowit.ts.trivia.app.screens.quiz.QuizRoute
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizInitViewModel @Inject constructor(private val routeNavigator: RouteNavigator) :
    ParentViewModel(routeNavigator) {

    init {
        Timber.d("ZZZ QuizInitViewModel init")
    }

    private val email = mutableStateOf("qinit@email")
    private val isSecretDifficultyVisible = mutableStateOf(false)

    internal val interactions = QuizInitScreenInteractions(
        onBackClicked = ::onBackClicked,
        onNavigateToQuiz = ::onNavigateToQuiz,
    )

    internal val state = QuizInitState(
        email = email,
        isSecretDifficultyVisible = isSecretDifficultyVisible,
    )

    private fun onNavigateToQuiz(difficulty: Difficulty) {
        Timber.d("ZZZ onNavigateToQuiz")
        navigateToRoute(QuizRoute.getRoute(difficulty))
    }
}


