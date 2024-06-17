package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty


// choose difficulty

@Composable
internal fun QuizInitScreen(
    modifier: Modifier = Modifier,
    state: QuizInitState = QuizInitState.stub(),
    interactions: QuizInitScreenInteractions = QuizInitScreenInteractions.STUB,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        Text(text = stringResource(id = R.string.select_difficulty), fontSize = 22.sp)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        DifficultyOption(interactions, Difficulty.EASY)
        DifficultyOption(interactions, Difficulty.MEDIUM)
        DifficultyOption(interactions, Difficulty.HARD)
        if (state.isSecretDifficultyVisible.value) DifficultyOption(
            interactions, Difficulty.WOJTEK
        )
    }
}

@Composable
private fun DifficultyOption(
    interactions: QuizInitScreenInteractions, difficulty: Difficulty
) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(20.dp),
        colors = ButtonDefaults.textButtonColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            interactions.onNavigateToQuiz(difficulty)
        })
    {
        Text(
            text = stringResource(id = difficulty.diffText),
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun QuizInitScreenPreview() {
    QuizInitScreen()
}
