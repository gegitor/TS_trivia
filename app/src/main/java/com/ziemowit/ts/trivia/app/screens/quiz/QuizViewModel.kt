package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.data.Difficulty
import com.ziemowit.ts.trivia.data.QuestionRepository
import kotlinx.coroutines.launch
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val questionRepository: QuestionRepository
) : ParentViewModel(routeNavigator) {
    //    private val difficulty: String = checkNotNull(savedStateHandle["difficultyId"])
//    private val diffArgs = QuizArgs(savedStateHandle)
    private val difficulty: State<Difficulty> = mutableStateOf(QuizArgs(savedStateHandle).difficulty)

    init {
        Timber.d("ZZZ QuizViewModel init diff: ${difficulty.value}")
        loadQuestions()
    }

    internal val interactions = QuizScreenInteractions(
        onBackClicked = ::onBackClicked,
    )

    internal val state = QuizState(
        difficulty = difficulty,
    )


    private fun loadQuestions() {
        viewModelScope.launch {
            val questions = questionRepository.getQuestions(difficulty.value)
            Timber.d("Loaded questions: $questions")
        }
    }
}
