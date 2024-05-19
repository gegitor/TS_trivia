package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


// choose difficulty

@Composable
internal fun QuizInitScreen(
    modifier: Modifier = Modifier,
    state: QuizInitState = QuizInitState.stub(),
    interactions: QuizInitScreenInteractions = QuizInitScreenInteractions.STUB,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.BottomCenter,
    ) {
        TextButton(onClick = { interactions.onNavigateToQuiz() }) {
            Text(text = "quiz init: ${state.email}", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun QuizInitScreenPreview() {
    QuizInitScreen()
}
