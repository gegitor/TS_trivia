package com.ziemowit.ts.trivia.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import timber.log.Timber

@Composable
fun QuizScreen(viewModel: QuizViewModel/*difficulty: String*/, modifier: Modifier = Modifier) {
    Timber.d("ZZZ difficulty: ${viewModel.difficulty.value}")
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(text = "Quiz ${viewModel.difficulty.value}", fontSize = 100.sp)
    }
}

