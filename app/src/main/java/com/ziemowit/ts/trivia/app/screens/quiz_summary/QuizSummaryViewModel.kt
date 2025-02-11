package com.ziemowit.ts.trivia.app.screens.quiz_summary

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ziemowit.ts.trivia.app.screens.main.ParentViewModel
import com.ziemowit.ts.trivia.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class QuizSummaryViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    routeNavigator: RouteNavigator,
    savedStateHandle: SavedStateHandle,
) :
    ParentViewModel(routeNavigator) {

    private val quizArgs = QuizArgs(savedStateHandle)

    private val difficulty = mutableStateOf(context.getString(quizArgs.difficulty.displayName))
    private val score = mutableStateOf("3 / 10")

    private val leaderRankings = listOf(
        "Nikita Khrushchev",
        "François Mitterrand",
        "Gustáv Husák",
        "Erich Honecker"
    )

    private val userRank = leaderRankings.first()

    // UI State
    val state = QuizSummaryState(
        difficulty = difficulty,
        score = score,
        leaderRankings = mutableStateOf(leaderRankings),
        userRank = mutableStateOf(userRank)
    )

    // UI Interactions
    val interactions = QuizSummaryInteractions(
        onPlayAgain = { /* TODO: Handle navigation */ },
        onReviewAnswers = { /* TODO: Handle review */ }
    )

    init {
        Timber.d("QuizSummaryViewModel init diff: ${difficulty.value} correctQuestions: ${quizArgs.correctQuestions} totalQuestions: ${quizArgs.totalQuestions}")

    }
}
