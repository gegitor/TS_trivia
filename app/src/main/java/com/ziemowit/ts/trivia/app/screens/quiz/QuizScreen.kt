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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.data.PotentialAnswer
import com.ziemowit.ts.ui_common.components.LoadingContent
import timber.log.Timber

//TODO - confirmation dialog for back arrow

@Composable
internal fun QuizScreen(
    modifier: Modifier = Modifier,
    state: QuizState = QuizState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB
) {
    if (state.isLoading.value) {
        LoadingContent(modifier)
    } else {
        QuestionContent(modifier, state, interactions)
    }
}

@Composable
private fun QuestionContent(
    modifier: Modifier = Modifier,
    state: QuizState = QuizState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display the question number and total questions
        Text(
            text = state.questionCount.value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )

        // Display the question text
        Text(
            text = state.question.value.question,
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
                items(state.question.value.potentialAnswers) { answer ->
                    val selected = remember { mutableStateOf(answer.selected) }
                    AnswerItem(
                        potentialAnswer = answer,
                        isEnabled = state.isAnswerEnabled.value,
                        isSelected = selected.value,
                        onAnswerSelected = { potentialAnswer ->
                            selected.value = true
                            interactions.onAnswerSelected(state.question.value.index, potentialAnswer)
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
    Timber.w("ZZZ AnswerItem isSelected: $isSelected, potentialAnswer: ${potentialAnswer.answerText}")
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


