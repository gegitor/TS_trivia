package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitScreenInteractions
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitState
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val routeNavigator: RouteNavigator) :
    ParentViewModel(routeNavigator) {
    //    private val difficulty: String = checkNotNull(savedStateHandle["difficultyId"])
//    private val diffArgs = QuizArgs(savedStateHandle)
    private val difficulty: State<Difficulty> = mutableStateOf(QuizArgs(savedStateHandle).difficulty)

    init {
        Timber.d("ZZZ", "ZZZ QuizViewModel init diff: ${difficulty.value}")

    }


    internal val interactions = QuizScreenInteractions(
        onBackClicked = ::onBackClicked,
    )

    internal val state = QuizState(
        difficulty = difficulty,
    )


    private fun onBackClicked() {
        Timber.d("ZZZ", "ZZZ onBackClicked")
        routeNavigator.navigateUp()
    }
}