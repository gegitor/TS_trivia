package com.ziemowit.ts.trivia.data

import android.content.Context
import com.ziemowit.ts.trivia.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestions(difficulty: Difficulty): List<Question>
    suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<Question>
}

class QuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : QuestionRepository {

    private var allQuestions: List<Question>? = null
    private val tokenCount: Int = 8

    override suspend fun getQuestions(difficulty: Difficulty): List<Question> {
        if (allQuestions == null) {
            allQuestions = readQuestionsFromFile()
        }
        return allQuestions.orEmpty().filter { it.difficulty.index == difficulty.index }
    }

    override suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<Question> {
        if (allQuestions == null) {
            allQuestions = readQuestionsFromFile()
        }
        return allQuestions.orEmpty().filter { it.difficulty.index <= difficulty.index }
    }

    private suspend fun readQuestionsFromFile(): List<Question> {
        val questions = mutableListOf<Question>()
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(inputStream))

        withContext(Dispatchers.IO) {
            reader.readLine() // Skip header line

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val tokens = it.split(",", ignoreCase = false)
                    Timber.w("Tokens size: ${tokens.size}")
                    if (tokens.size == tokenCount) {
                        try {
                            val index = tokens[0].toInt()
                            val category = Category.valueOf(tokens[1])
                            val diff = Difficulty.entries[tokens[2].toInt()]
                            val answer = tokens[3]
                            val wrongAnswers = tokens.drop(4)
                            questions.add(Question(index, category, diff, answer, wrongAnswers))
                        } catch (e: Exception) {
                            Timber.e(e, "Error parsing line: $it")
                        }
                    } else {
                        Timber.w("Invalid token count: $it")
                    }
                }
            }
            reader.close()
        }
        return questions
    }
}
