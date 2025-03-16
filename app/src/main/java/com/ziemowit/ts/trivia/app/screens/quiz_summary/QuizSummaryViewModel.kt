package com.ziemowit.ts.trivia.app.screens.quiz_summary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.home.HomeRoute
import com.ziemowit.ts.trivia.app.screens.quiz.QuizRoute
import com.ziemowit.ts.trivia.data.LeaderRepository
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizSummaryViewModel @Inject constructor(
    leaderRepository: LeaderRepository,
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
) :
    ParentViewModel(routeNavigator) {

    private val quizArgs = QuizArgs(savedStateHandle)

    private val difficulty = mutableStateOf(quizArgs.difficulty)
    private val score = mutableStateOf("${quizArgs.correctQuestions} / ${quizArgs.totalQuestions}")

    private val leaderRankings =
        mutableStateOf(
            leaderRepository.getLeaderboard(quizArgs.difficulty.scoreMultiplier() * quizArgs.correctQuestions.toFloat() / quizArgs.totalQuestions)
                .map { it.leaderName })


    private val userRank = mutableStateOf(leaderRankings.value.first())

    // UI State
    val state = QuizSummaryState(
        difficulty = difficulty,
        score = score,
        leaderRankings = leaderRankings,
        userRank = userRank,
    )

    // UI Interactions
    val interactions = QuizSummaryInteractions(
        onPlayAgain = { navigateToRouteWithPop(QuizRoute.getRoute(difficulty.value), QuizSummaryRoute.route) },
        onMainMenu = { popToRoute(HomeRoute.route) }
    )

    init {
        Timber.d("QuizSummaryViewModel init diff: ${difficulty.value} correctQuestions: ${quizArgs.correctQuestions} totalQuestions: ${quizArgs.totalQuestions}")
        Timber.d("QuizSummaryViewModel init leaderRankings: ${leaderRankings.value}")

    }
}

private fun Difficulty.scoreMultiplier() =
    when (this) {
        Difficulty.EASY -> 0.4f
        Difficulty.MEDIUM -> 0.6f
        Difficulty.HARD -> 0.8f
        Difficulty.WOJTEK -> 1.0f
    }
