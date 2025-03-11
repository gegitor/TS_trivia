package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    interactions: HomeScreenInteractions
) {

    Box(modifier = modifier.fillMaxSize()) {
        when {
//            state.isLoading -> LoadingContent()
            else -> MainContent(
                state = state,
                interactions = interactions,
            )
        }

//        state.errorMessage?.let { errorMessage ->
//            ErrorDialog(errorMessage = errorMessage, onDismiss = { interactions.dismissError() })
//        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color(0xFF673AB7))
    }
}

@Composable
private fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error") },
        text = { Text(errorMessage) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
private fun MainContent(
    state: HomeState = HomeState.stub(),
    interactions: HomeScreenInteractions = HomeScreenInteractions.STUB,
) {
    val primaryColor = Color(0xFF673AB7)
    val accentColor = Color(0xFFFF5722)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            WelcomeSection(userName = state.userName.value)
        }

        state.continueQuiz.value?.let { continueQuiz ->
            item {
                ContinueQuizCard(
                    continueQuiz = continueQuiz,
                    onContinueQuizClick = { interactions.onContinueQuizClick() }
                )
            }
        }

        item {
            QuickPlayButton(
                onQuickPlayClick = { interactions.onQuickPlayAddClick() },
                primaryColor = primaryColor
            )
        }

        item {
            CustomQuizButton(
                onCreateCustomQuizClick = { interactions.onCreateCustomQuizClick() },
                primaryColor = primaryColor
            )
        }

        item {
            RecentActivityHeader()
        }

        items(state.recentActivities.value) { activity ->
            RecentActivityItem(
                activity = activity,
                onChallengeAcceptClick = { interactions.onChallengeAcceptClick(it) },
                onShareClick = { interactions.onShareClick(it) },
                accentColor = accentColor,
                primaryColor = primaryColor
            )
        }
    }
}

@Composable
private fun WelcomeSection(userName: String) {
    Text(
        text = "Welcome back, $userName!",
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
        fontSize = 18.sp
    )
}

@Composable
private fun ContinueQuizCard(
    continueQuiz: HomeState.ContinueQuizInfo,
    onContinueQuizClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF009688)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "▶",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Continue your ${continueQuiz.categoryName} Quiz",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${continueQuiz.questionsCompleted}/${continueQuiz.totalQuestions} questions completed",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            IconButton(onClick = onContinueQuizClick) {
                Text(
                    text = "▶",
                    color = Color(0xFF009688),
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
private fun QuickPlayButton(
    onQuickPlayClick: () -> Unit,
    primaryColor: Color
) {
    Button(
        onClick = onQuickPlayClick,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .height(50.dp)
            .width(200.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor
        )
    ) {
        Text(
            text = "Quick Play",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun CustomQuizButton(
    onCreateCustomQuizClick: () -> Unit,
    primaryColor: Color
) {
    OutlinedButton(
        onClick = onCreateCustomQuizClick,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .height(50.dp)
            .width(200.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = primaryColor
        ),
        border = ButtonDefaults.outlinedButtonBorder().copy(
            width = 2.dp,
            brush = SolidColor(primaryColor)
        )
    ) {
        Text(
            text = "Create Custom Quiz",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RecentActivityHeader() {
    Text(
        text = "Recent Activity",
        modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RecentActivityItem(
    activity: HomeState.ActivityItem,
    onChallengeAcceptClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    accentColor: Color,
    primaryColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActivityAvatar(activity)
            ActivityContent(activity, Modifier
                .weight(1f)
                .padding(start = 16.dp))
            ActivityButton(
                activity = activity,
                accentColor = accentColor,
                primaryColor = primaryColor,
                onChallengeAcceptClick = onChallengeAcceptClick,
                onShareClick = onShareClick,
            )
        }
    }
}

@Composable
private fun ActivityAvatar(activity: HomeState.ActivityItem) {
    val (backgroundColor, initial) = when (activity) {
        is HomeState.ActivityItem.Challenge -> Pair(Color(0xFF3F51B5), "M")
        is HomeState.ActivityItem.ScoreComparison -> Pair(Color(0xFF009688), "S")
        is HomeState.ActivityItem.Achievement -> Pair(Color(0xFFF44336), "J")
    }

    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ActivityContent(
    activity: HomeState.ActivityItem,
    modifier: Modifier = Modifier
) {
    val title = when (activity) {
        is HomeState.ActivityItem.Challenge -> "${activity.fromUserName} challenged you!"
        is HomeState.ActivityItem.ScoreComparison -> "You beat ${activity.otherUserName}'s score!"
        is HomeState.ActivityItem.Achievement -> activity.title
    }

    val description = when (activity) {
        is HomeState.ActivityItem.Challenge ->
            "${activity.categoryName} Quiz - ${activity.difficultyLevel.name}"

        is HomeState.ActivityItem.ScoreComparison ->
            "${activity.yourScore}% vs ${activity.theirScore}% - ${activity.categoryName} Quiz"

        is HomeState.ActivityItem.Achievement ->
            "${activity.quizName} - ${activity.description}"
    }

    Column(modifier = modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        Text(
            text = description,
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun ActivityButton(
    activity: HomeState.ActivityItem,
    accentColor: Color,
    primaryColor: Color,
    onChallengeAcceptClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
) {
    val (buttonColor, buttonText, onClick) = when (activity) {
        is HomeState.ActivityItem.Challenge -> Triple(
            accentColor,
            "ACCEPT"
        ) { onChallengeAcceptClick(activity.id) }

        is HomeState.ActivityItem.ScoreComparison -> Triple(
            primaryColor,
            "SHARE"
        ) { onShareClick(activity.id) }

        is HomeState.ActivityItem.Achievement -> Triple(
            primaryColor,
            "SHARE"
        ) { onShareClick(activity.id) }
    }

    Button(
        onClick = onClick,
        modifier = Modifier.height(30.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    MainContent()
}