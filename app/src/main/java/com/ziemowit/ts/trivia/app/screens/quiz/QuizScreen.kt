package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitScreen
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitScreenInteractions
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitState
import com.ziemowit.ts.trivia.app.screens.quiz_init.QuizInitViewModel
import com.ziemowit.ts.trivia.nav.NavRoute
import timber.log.Timber

@Composable
internal fun QuizScreen(
    modifier: Modifier = Modifier,
    state: QuizState = QuizState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB,
) {
    Timber.d("ZZZ difficulty: ${state.difficulty.value}")
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(text = "Quiz ${state.difficulty.value}", fontSize = 100.sp)
    }
}
