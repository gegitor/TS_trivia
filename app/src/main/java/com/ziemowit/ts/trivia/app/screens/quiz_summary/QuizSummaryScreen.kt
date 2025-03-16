package com.ziemowit.ts.trivia.app.screens.quiz_summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.ui_common.theme.TSColor
import timber.log.Timber

@Composable
fun QuizSummaryScreen(
    state: QuizSummaryState = QuizSummaryState.stub(),
    interactions: QuizSummaryInteractions = QuizSummaryInteractions.STUB,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TSColor.Background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header

        DifficultyChip(Modifier.padding(12.dp), state.difficulty.value)

        Text(
            text = stringResource(R.string.quiz_summary_score, state.score.value),
            fontSize = 24.sp,
            color = TSColor.Gold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            LeaderRankingsContent(state)

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ActionButton(
                    text = stringResource(R.string.quiz_summary_play_again),
                    color = TSColor.Red,
                    onClick = interactions.onPlayAgain,
                )
                ActionButton(
                    text = stringResource(R.string.quiz_summary_main_menu),
                    color = TSColor.Blue,
                    onClick = interactions.onMainMenu,
                )
            }
        }
    }
}

@Composable
fun ColumnScope.LeaderRankingsContent(state: QuizSummaryState) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .weight(1.0f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = stringResource(R.string.quiz_summary_achieved_level),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TSColor.TextColor
            )
        }
        item {
            // Player's Cold War Standing
            LeaderRankingCard(leaderName = state.userRank.value, isPlayer = true)
            Spacer(modifier = Modifier.height(16.dp))
        }
        state.leaderRankings.value.drop(1).let { leaderList ->
            items(leaderList.size) { index ->
                LeaderRankingCard(leaderName = leaderList[index], isPlayer = false)
            }
        }
    }
}

// Reusable Card for Leader Rankings
@Composable
fun LeaderRankingCard(leaderName: String, isPlayer: Boolean) {
    val backgroundColor = if (isPlayer) TSColor.Gold else TSColor.CardBackground
    val textColor = if (isPlayer) Color.Black else TSColor.TextColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = if (isPlayer) stringResource(R.string.quiz_summary_player_rank, leaderName) else leaderName,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun DifficultyChip(modifier: Modifier = Modifier, difficulty: Difficulty) {
    Timber.d("DifficultyChip difficulty: $difficulty!")

    val color = when (difficulty) {
        Difficulty.EASY -> TSColor.Green
        Difficulty.MEDIUM -> TSColor.Yellow
        Difficulty.HARD -> TSColor.Red
        Difficulty.WOJTEK -> TSColor.Bronze
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, TSColor.CardBorder),
        colors = CardDefaults.cardColors(containerColor = TSColor.CardBackground)
    ) {
        Text(
            text = stringResource(R.string.quiz_summary_difficulty, stringResource(difficulty.displayName)),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    }
}

// Reusable Action Button
@Composable
fun ActionButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .height(48.dp)
            .width(160.dp)
    ) {
        Text(text = text, fontSize = 16.sp, color = Color.White)
    }
}

@Preview
@Composable
private fun QuizSummaryScreenPreview() {
    QuizSummaryScreen()
}
