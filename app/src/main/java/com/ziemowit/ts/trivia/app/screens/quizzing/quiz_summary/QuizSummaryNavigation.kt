package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ziemowit.ts.trivia.domain.model.Difficulty
import com.ziemowit.ts.trivia.nav.NavRoute

private const val difficultyArg = "difficulty"
private const val correctQuestionsArg = "correctQuestions"
private const val totalQuestionsArg = "totalQuestions"


internal class QuizArgs(val difficulty: Difficulty, val correctQuestions: Int, val totalQuestions: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                Difficulty.fromOrdinal(checkNotNull(savedStateHandle[difficultyArg])),
                checkNotNull(savedStateHandle[correctQuestionsArg]),
                checkNotNull(savedStateHandle[totalQuestionsArg])
            )
}


object QuizSummaryRoute : NavRoute<QuizSummaryViewModel> {

    override val route =
        "quizSummary?$difficultyArg={$difficultyArg}&${correctQuestionsArg}={${correctQuestionsArg}}&${totalQuestionsArg}={${totalQuestionsArg}}"

    @Composable
    override fun viewModel(): QuizSummaryViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: QuizSummaryViewModel) =
//        SummaryContent(viewModel.state, viewModel.interactions)
        QuizSummaryScreen(viewModel.state, viewModel.interactions)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(difficultyArg) { type = NavType.IntType },
        navArgument(correctQuestionsArg) { type = NavType.IntType },
        navArgument(totalQuestionsArg) { type = NavType.IntType },
    )

    fun getRoute(difficulty: Difficulty, correctQuestions: Int, totalQuestions: Int) =
        route.replace("{$difficultyArg}", "${difficulty.ordinal}")
            .replace("{${correctQuestionsArg}}", "$correctQuestions")
            .replace("{${totalQuestionsArg}}", "$totalQuestions")
}