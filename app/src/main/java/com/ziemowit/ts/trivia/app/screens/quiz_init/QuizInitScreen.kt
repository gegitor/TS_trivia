package com.ziemowit.ts.trivia.app.screens.quiz_init

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.ui_common.theme.TSColor


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
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Text(text = stringResource(id = R.string.select_difficulty), fontSize = 22.sp)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        DifficultyOption(interactions, Difficulty.EASY)
        DifficultyOption(interactions, Difficulty.MEDIUM)
        DifficultyOption(interactions, Difficulty.HARD)
        AnimatedVisibility(!state.isSecretDifficultyVisible.value) {
            HiddenDifficultyDetector(interactions)
        }
        AnimatedVisibility(state.isSecretDifficultyVisible.value) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                DifficultyOption(
                    interactions, Difficulty.WOJTEK
                )
                HideSecretDifficultyButton { interactions.setHiddenDifficultyVisibility(false) }
            }
        }

    }
}

@Composable
private fun DifficultyOption(
    interactions: QuizInitScreenInteractions, difficulty: Difficulty
) {
    TextButton(
        modifier = Modifier
            .padding(20.dp),
        colors = ButtonDefaults.textButtonColors(),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            interactions.onNavigateToQuiz(difficulty)
        })
    {
        Text(
            text = stringResource(id = difficulty.displayName),
            fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HiddenDifficultyDetector(
    interactions: QuizInitScreenInteractions
) {
    Box(
        modifier = Modifier
            .height(54.dp)
            .padding(30.dp)
            .combinedClickable(
                onClick = {
                    /* no-op */
                },
                onDoubleClick = {
                    interactions.setHiddenDifficultyVisibility(true)
                })
    )
}

@Composable
fun HideSecretDifficultyButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(32.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = stringResource(R.string.acc_hide_secret_difficulty),
            modifier = Modifier.fillMaxSize(),
            tint = TSColor.Red40
        )
    }
}

@Preview
@Composable
private fun QuizInitScreenPreview() {
    QuizInitScreen()
}
