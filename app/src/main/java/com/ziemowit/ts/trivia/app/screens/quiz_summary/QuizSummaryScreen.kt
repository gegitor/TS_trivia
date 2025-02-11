package com.ziemowit.ts.trivia.app.screens.quiz_summary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizSummaryScreen(
    state: QuizSummaryState = QuizSummaryState.stub(),
    interactions: QuizSummaryInteractions = QuizSummaryInteractions.STUB,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2A47)) // Dark blue background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🎖 Twilight Struggle Quiz 🎖",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Difficulty: ${state.difficulty.value}",
            color = Color.White,
            fontSize = 18.sp
        )

        Text(
            text = "Score: ${state.score.value}",
            color = Color.White,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Player's Cold War Standing
        LeaderRankingCard(leaderName = state.userRank.value, isPlayer = true)

        Spacer(modifier = Modifier.height(16.dp))

        // Animated Leader Rankings Below
        state.leaderRankings.value.drop(1).forEachIndexed { index, leader ->
            AnimatedVisibility(
                visible = true,
                enter = androidx.compose.animation.fadeIn(animationSpec = tween(500 * (index + 1)))
            ) {
                LeaderRankingCard(leaderName = leader, isPlayer = false)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton(text = "🔄 Play Again", color = Color.Red, onClick = interactions.onPlayAgain)
            ActionButton(text = "📚 Review Answers", color = Color.Blue, onClick = interactions.onReviewAnswers)
        }
    }
}

// Reusable Card for Leader Rankings
@Composable
fun LeaderRankingCard(leaderName: String, isPlayer: Boolean) {
    val backgroundColor = if (isPlayer) Color(0xFFFFC107) else Color(0xFF2C3E50)
    val textColor = if (isPlayer) Color.Black else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = if (isPlayer) "⭐ $leaderName (You)" else leaderName,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
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
        Text(text = text, fontSize = 16.sp)
    }
}
