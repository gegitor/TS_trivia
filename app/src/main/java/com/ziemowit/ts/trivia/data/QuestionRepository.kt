package com.ziemowit.ts.trivia.data

import android.content.Context
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.Category
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.data.model.QuestionEntry
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestions(difficulty: Difficulty): List<QuestionEntry>
    suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<QuestionEntry>
}

class QuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : QuestionRepository {

    private var allQuestionEntries: List<QuestionEntry>? = null
    private val tokenCount: Int = 9

    override suspend fun getQuestions(difficulty: Difficulty): List<QuestionEntry> {
        if (allQuestionEntries == null) {
            allQuestionEntries = readQuestionsFromFile()
        }
        return allQuestionEntries.orEmpty().filter { it.difficulty.index == difficulty.index }
    }

    override suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<QuestionEntry> {
        if (allQuestionEntries == null) {
            allQuestionEntries = readQuestionsFromFile()
        }
        return allQuestionEntries.orEmpty().filter { it.difficulty.index <= difficulty.index }
    }

    private suspend fun readQuestionsFromFile(): List<QuestionEntry> {
        val questionEntries = mutableListOf<QuestionEntry>()
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(inputStream))

        withContext(Dispatchers.IO) {
            reader.readLine() // Skip header line

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val tokens = it.split(",")
                    if (tokens.size == tokenCount) {
                        try {
                            val index = tokens[0].toInt()
                            val category = Category.valueOf(tokens[1])
                            val diff = Difficulty.entries[tokens[2].toInt()]
                            val question = tokens[3]
                            val answer = tokens[4]
                            val wrongAnswers = tokens.drop(5)
                            questionEntries.add(QuestionEntry(index, category, diff, question, answer, wrongAnswers))
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
        return questionEntries
    }
}
