package com.ziemowit.ts.trivia.app.screens.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziemowit.ts.trivia.data.PotentialAnswer

@Composable
internal fun QuizScreen(
    modifier: Modifier = Modifier,
    state: QuizState,
    interactions: QuizScreenInteractions,
) {
    QuestionContent(modifier, state, interactions)
}

@Composable
private fun QuestionContent(
    modifier: Modifier = Modifier,
    state: QuizState = QuizState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB,
) {
    val currentQuestionIndex = state.currentQuestionIndex.value
    val question = state.questions.value.getOrNull(currentQuestionIndex)

    if (question != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Display the question number and total questions
            Text(
                text = "Question ${currentQuestionIndex + 1} of ${state.questions.value.size}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Display the question text
            Text(
                text = question.question,
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
                    itemsIndexed(question.potentialAnswers) { index, answer ->
                        AnswerItem(
                            potentialAnswer = answer,
                            isAnswered = state.isAnswerSelected.value,
                            isSelected = true,
                            onAnswerSelected = { potentialAnswer ->
                                interactions.onAnswerSelected(currentQuestionIndex, potentialAnswer)
                            }
                        )
                    }
                }
            }

            // Next Question Button
            Button(
                onClick = { interactions.onNextQuestion() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = if (currentQuestionIndex < state.questions.value.size - 1) "Next Question" else "Finish Quiz",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun AnswerItem(
    potentialAnswer: PotentialAnswer,
    isSelected: Boolean,
    isAnswered: Boolean,
    onAnswerSelected: (PotentialAnswer) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isAnswered) { onAnswerSelected(potentialAnswer) },
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
