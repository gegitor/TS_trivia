package com.ziemowit.ts.trivia.data

import android.content.Context
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.app.screens.quiz.Difficulty
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestions(difficulty: Difficulty): List<Question>
}

class QuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : QuestionRepository {
    override suspend fun getQuestions(difficulty: Difficulty): List<Question> {
        val questions = mutableListOf<Question>()
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(inputStream))

        withContext(Dispatchers.IO) {
            reader.readLine()
            // Skip header line

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",", ignoreCase = false, limit = 9)
                if (tokens.size == 9) {
                    val category = Category.entries.first { it.index == tokens[1].toInt() }
                    val diff = Difficulty.entries.first { it.value == tokens[3].toInt() }
                    val answer = tokens[4]
                    val wrongAnswers = listOf(tokens[5], tokens[6], tokens[7], tokens[8])
                    questions.add(Question(category, diff, answer, wrongAnswers))
                }
            }

            reader.close()
        }
        return questions.filter { it.difficulty == difficulty }
    }
}
