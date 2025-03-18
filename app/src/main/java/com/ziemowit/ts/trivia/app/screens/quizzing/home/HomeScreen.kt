package com.ziemowit.ts.trivia.app.screens.quizzing.home

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.ui_common.components.buttons.PrimaryActionButton
import com.ziemowit.ts.ui_common.components.buttons.SecondaryActionButton
import com.ziemowit.ts.ui_common.theme.TSColor
import com.ziemowit.ts.ui_common.theme.TSTriviaTheme

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState.stub(),
    interactions: HomeScreenInteractions = HomeScreenInteractions.STUB,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            WelcomeSection(userName = state.userName.value)
        }

        state.continueQuiz.value?.let { continueQuiz ->
            item {
                ContinueQuizCard(continueQuiz = continueQuiz, onContinueQuizClick = { interactions.onContinueQuizClick() })
            }
        }

        item {
            PrimaryActionButton(stringResource(R.string.quick_play), { interactions.onQuickPlayClick() }, Modifier.padding(top = 12.dp))
        }

        item {
            SecondaryActionButton(
                stringResource(R.string.create_custom_quiz),
                { interactions.onCreateCustomQuizClick() },
                Modifier.padding(top = 8.dp, bottom = 12.dp)
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
            )
        }
    }
}

@Composable
fun WelcomeSection(
    userName: String, modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(R.string.welcome))
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                )
            ) {
                append(userName)
            }
            append("!")
        }, style = MaterialTheme.typography.headlineSmall, modifier = modifier.padding(vertical = 8.dp)
    )
}


@Composable
private fun ContinueQuizCard(
    continueQuiz: HomeState.ContinueQuizInfo, onContinueQuizClick: () -> Unit
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
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF009688)), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "▶", color = Color.White, fontSize = 20.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.continue_quiz, continueQuiz.categoryName), fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
                Text(
                    text = stringResource(R.string.questions_completed, continueQuiz.questionsCompleted, continueQuiz.totalQuestions),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            IconButton(onClick = onContinueQuizClick) {
                Text(
                    text = "▶", color = Color(0xFF009688), fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
private fun RecentActivityHeader() {
    Text(
        text = stringResource(R.string.recent_activity),
        modifier = Modifier.padding(top = 24.dp, bottom = 10.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun RecentActivityItem(
    activity: HomeState.ActivityItem,
    onChallengeAcceptClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
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
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            ActivityAvatar(activity)
            ActivityContent(
                activity, Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
            ActivityButton(
                activity = activity,
                accentColor = TSColor.Blue,
                primaryColor = TSColor.Red,
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
            .background(backgroundColor), contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial, color = Color.White, fontSize = 12.sp
        )
    }
}

@Composable
private fun ActivityContent(
    activity: HomeState.ActivityItem, modifier: Modifier = Modifier
) {
    val title = when (activity) {
        is HomeState.ActivityItem.Challenge -> stringResource(R.string.challenged_you, activity.fromUserName)
        is HomeState.ActivityItem.ScoreComparison -> stringResource(R.string.you_beat, activity.otherUserName)
        is HomeState.ActivityItem.Achievement -> activity.title
    }

    val description = when (activity) {
        is HomeState.ActivityItem.Challenge -> stringResource(R.string.challenge_description, activity.categoryName, activity.difficultyLevel.name)

        is HomeState.ActivityItem.ScoreComparison -> stringResource(R.string.score_comparison_description, activity.yourScore, activity.theirScore, activity.categoryName)

        is HomeState.ActivityItem.Achievement -> stringResource(R.string.achievement_description, activity.quizName, activity.description)
    }

    Column(modifier = modifier) {
        Text(
            text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp
        )

        Text(
            text = description, color = Color.Gray, fontSize = 13.sp
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
            accentColor, R.string.accept
        ) { onChallengeAcceptClick(activity.id) }

        is HomeState.ActivityItem.ScoreComparison -> Triple(
            primaryColor, R.string.share
        ) { onShareClick(activity.id) }

        is HomeState.ActivityItem.Achievement -> Triple(
            primaryColor, R.string.share
        ) { onShareClick(activity.id) }
    }

    Button(
        onClick = onClick, modifier = Modifier.height(30.dp), shape = RoundedCornerShape(15.dp), colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ), contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(buttonText).uppercase(), fontSize = 12.sp, fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    TSTriviaTheme {
        HomeScreen()
    }
}
