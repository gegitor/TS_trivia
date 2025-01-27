package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.data.PotentialAnswer
import com.ziemowit.ts.trivia.data.QuestionInfo
import com.ziemowit.ts.ui_common.components.LoadingContent

//TODO - confirmation dialog for back arrow

@Composable
internal fun QuizScreen(
    modifier: Modifier = Modifier,
    state: QuizLoadingState = QuizLoadingState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB,
) {
    when (state) {
        is QuizLoading -> {
            LoadingContent()
        }

        is QuizReady -> {
            val quizState = state.quizState
            AnimatedContent(
                targetState = quizState.question.value,
                transitionSpec = {
                    (slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(300)
                    ) + fadeIn()).togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(300)
                        ) + fadeOut()
                    )
                }
            ) { targetState ->
                QuestionContent(
                    modifier,
                    targetState,
                    quizState.questionCount.value,
                    quizState.isAnswerEnabled.value,
                    interactions
                )
            }
        }

        is QuizLoadError -> {
            //TODO - add error screen
        }
    }
}

@Composable
private fun QuestionContent(
    modifier: Modifier = Modifier,
    question: QuestionInfo,
    questionCount: String,
    isAnswerEnabled: Boolean,
    interactions: QuizScreenInteractions,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display the question number and total questions
        Text(
            text = questionCount,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )

        // Display the question text
        Text(
            text = question.questionText,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Display the potential answers
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(question.potentialAnswers) { answer ->
                    AnswerItem(
                        potentialAnswer = answer,
                        isEnabled = isAnswerEnabled,
                        isSelected = answer.selected,
                        onAnswerSelected = { potentialAnswer ->
                            interactions.onAnswerSelected(question.index, potentialAnswer)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AnswerItem(
    potentialAnswer: PotentialAnswer,
    isEnabled: Boolean,
    isSelected: Boolean,
    onAnswerSelected: (PotentialAnswer) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isEnabled && !isSelected) { onAnswerSelected(potentialAnswer) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = potentialAnswer.answerText,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}


