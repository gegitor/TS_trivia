package com.ziemowit.ts.trivia.app.screens.quiz

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
    QuestionContent(state, interactions, modifier)
}

@Composable
private fun QuestionContent(
    state: QuizState = QuizState.stub(),
    interactions: QuizScreenInteractions = QuizScreenInteractions.STUB,
    modifier: Modifier,
) {
    val currentQuestionIndex = state.currentQuestionIndex.value
    val question = state.questions.value.getOrNull(currentQuestionIndex)
    if (question != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Question ${currentQuestionIndex + 1} / ${state.questions.value.size}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    LazyColumn {
                        itemsIndexed(question.potentialAnswers) { index, answer ->
                            AnswerItem(
                                potentialAnswer = answer,
//                                isSelected = state.userAnswers.value.getOrNull(currentQuestionIndex) == index,
                                onAnswerSelected = { potentialAnswer ->
                                    interactions.onAnswerSelected(currentQuestionIndex, potentialAnswer)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AnswerItem(
    potentialAnswer: PotentialAnswer,
    isSelected: Boolean = true,
    onAnswerSelected: (PotentialAnswer) -> Unit,
) {
    Button(
        onClick = { onAnswerSelected(potentialAnswer) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
        ),
    ) {
        Text(potentialAnswer.answerText)
    }
}
