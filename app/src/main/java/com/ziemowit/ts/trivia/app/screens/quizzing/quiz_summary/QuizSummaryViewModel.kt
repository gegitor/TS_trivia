package com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary

import android.content.Context
import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.core.utils.shareText
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.ParentViewModel
import com.ziemowit.ts.trivia.app.screens.quizzing.home.HomeRoute
import com.ziemowit.ts.trivia.app.screens.quizzing.quiz.QuizRoute
import com.ziemowit.ts.trivia.domain.model.Difficulty
import com.ziemowit.ts.trivia.domain.repository.LeaderRepository
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizSummaryViewModel @Inject constructor(
    leaderRepository: LeaderRepository,
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
    private val resources: Resources,
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
        onPlayAgain = { navigateToRouteWithPop(QuizRoute.getRoute(difficulty.value), com.ziemowit.ts.trivia.app.screens.quizzing.quiz_summary.QuizSummaryRoute.route) },
        onMainMenu = { popToRoute(HomeRoute.route) },
        onShareScore = ::shareScore,
    )

    init {
        Timber.d("QuizSummaryViewModel init diff: ${difficulty.value} correctQuestions: ${quizArgs.correctQuestions} totalQuestions: ${quizArgs.totalQuestions}")
        Timber.d("QuizSummaryViewModel init leaderRankings: ${leaderRankings.value}")
    }

    private fun shareScore(context: Context) {
        shareText(
            context,
            resources.getString(R.string.share_score, score.value, difficulty.value),
            resources.getString(R.string.share_score_title)
        )
    }
}

private fun Difficulty.scoreMultiplier() =
    when (this) {
        Difficulty.EASY -> 0.4f
        Difficulty.MEDIUM -> 0.6f
        Difficulty.HARD -> 0.8f
        Difficulty.WOJTEK -> 1.0f
    }
