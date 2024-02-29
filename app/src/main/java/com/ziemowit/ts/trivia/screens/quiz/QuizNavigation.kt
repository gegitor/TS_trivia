package com.ziemowit.ts.trivia.screens.quiz

import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ziemowit.ts.trivia.screens.main.Screen

fun NavController.navigateToQuizScreen(difficulty: String) {
    this.navigate("${Screen.Quiz.route}/$difficulty")
}

private const val difficultyArg = "difficulty"

fun NavGraphBuilder.quizScreen(/*onNavigateBack: () -> Unit*/) {
    composable("quiz/{$difficultyArg}") {
        val viewModel: QuizViewModel = hiltViewModel()
        QuizScreen(
            viewModel = viewModel,
//            onNavigateBack = onNavigateBack
        )
    }
}


internal class QuizArgs(val difficulty: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[difficultyArg]) as String)
}
