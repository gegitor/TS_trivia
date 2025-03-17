package com.ziemowit.ts.trivia.app.screens.quiz_summary

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun SummaryContent(
    state: QuizSummaryState = QuizSummaryState.stub(),
    interactions: QuizSummaryInteractions = QuizSummaryInteractions.STUB,
) {
    Scaffold(
        topBar = { SummaryTopBar(state) },
        bottomBar = { ActionButtonsa(interactions) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                LeaderComparisonSection(state)
            }
        }
    }
}

@Composable
private fun SummaryTopBar(state: QuizSummaryState) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        AnimatedScoreStars(state)
        Spacer(modifier = Modifier.height(8.dp))
        DifficultyChip(state.difficulty.value.name)
    }
}

@Composable
private fun AnimatedScoreStars(state: QuizSummaryState) {
    var animationPlayed by remember { mutableStateOf(false) }
    val stars = 3//state.starRating

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = state.score.value,
            style = MaterialTheme.typography.displaySmall,
        )

        Row {
            repeat(5) { index ->
                val scale by animateFloatAsState(
                    targetValue = if (animationPlayed && index < stars) 1.2f else 1f,
                    animationSpec = tween(durationMillis = 300, delayMillis = index * 100),
                )

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .scale(scale),
                    tint = if (index < stars) Color.Yellow else Color.Gray,
                )
            }
        }
    }
}

@Composable
private fun DifficultyChip(difficulty: String) {
    val color = when (difficulty) {
        "Easy" -> Color.Green
        "Medium" -> Color.Yellow
        "Hard" -> Color.Red
        else -> Color.Gray
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color),
    ) {
        Text(
            text = difficulty,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = color,
        )
    }
}

@Composable
private fun LeaderComparisonSection(state: QuizSummaryState) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TypewriterText(
            text = "You are most like: ${state.userRank.value}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        LeaderList(leaders = state.leaderRankings.value)
    }
}

@Composable
private fun TypewriterText(text: String, style: TextStyle) {
    var visibleText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        text.forEachIndexed { index, _ ->
            visibleText = text.take(index + 1)
            delay(50)
        }
    }

    Text(
        text = visibleText,
        style = style,
        modifier = Modifier.animateContentSize()
    )
}

@Composable
private fun LeaderList(leaders: List<String>) {
    // No more LazyColumn here, it is now the parent.
    leaders.forEachIndexed { index, leader ->
        AnimatedLeaderItem(leader, index)
    }
}

@Composable
private fun AnimatedLeaderItem(leader: String, index: Int) {
    val offsetX by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = index * 100,
            easing = FastOutSlowInEasing
        ),
//        initialValue = 1000f
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .offset(x = offsetX.dp),
//        elevation = 4.dp
    ) {
        ListItem(
            headlineContent = {
                Text(leader, style = MaterialTheme.typography.titleMedium)
            },
            supportingContent = {
                Text(leader, style = MaterialTheme.typography.bodyMedium)
            }
        )
    }
}

@Composable
private fun ActionButtonsa(interactions: QuizSummaryInteractions) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AnimatedButton(
            text = "Retry",
            onClick = { interactions.onPlayAgain() }
        )
        AnimatedButton(
            text = "Main Menu",
            onClick = { interactions.onMainMenu() }
        )
    }
}

@Composable
private fun AnimatedButton(text: String, onClick: () -> Unit) {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        targetValue = if (buttonState == ButtonState.Pressed) 0.9f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        buttonState = ButtonState.Pressed
                        tryAwaitRelease()
                        buttonState = ButtonState.Idle
                    }
                )
            }
    ) {
        Text(text = text)
    }
}

private enum class ButtonState { Idle, Pressed }

@Preview
@Composable
private fun QuizSummaryScreen2Preview() {
    SummaryContent()
}
