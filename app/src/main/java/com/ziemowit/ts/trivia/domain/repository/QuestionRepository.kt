package com.ziemowit.ts.trivia.domain.repository

import android.content.Context
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.file.CategoryFile
import com.ziemowit.ts.trivia.data.model.file.DifficultyFile
import com.ziemowit.ts.trivia.data.model.file.QuestionFile
import com.ziemowit.ts.trivia.di.DispatcherProvider
import com.ziemowit.ts.trivia.domain.model.Difficulty
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestions(difficulty: Difficulty): List<QuestionFile>
    suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<QuestionFile>
}

class QuestionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatchers: DispatcherProvider,
) : QuestionRepository {

    private var allQuestionEntries: List<QuestionFile>? = null
    private val tokenCount: Int = 9

    override suspend fun getQuestions(difficulty: Difficulty): List<QuestionFile> {
        if (allQuestionEntries == null) {
            allQuestionEntries = readQuestionsFromFile()
        }
        return allQuestionEntries.orEmpty().filter { it.difficultyFile.index == difficulty.index }
    }

    override suspend fun getQuestionsWithMaxDiff(difficulty: Difficulty): List<QuestionFile> {
        if (allQuestionEntries == null) {
            allQuestionEntries = readQuestionsFromFile()
        }
        return allQuestionEntries.orEmpty().filter { it.difficultyFile.index <= difficulty.index }
    }

    private suspend fun readQuestionsFromFile(): List<QuestionFile> {
        val questionEntries = mutableListOf<QuestionFile>()
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val reader = BufferedReader(InputStreamReader(inputStream))

        withContext(dispatchers.io) {
            reader.readLine() // Skip header line

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val tokens = it.split(",")
                    if (tokens.size == tokenCount) {
                        try {
                            val index = tokens[0].toInt()
                            val category = CategoryFile.valueOf(tokens[1])
                            val diff = DifficultyFile.entries[tokens[2].toInt()]
                            val question = tokens[3]
                            val answer = tokens[4]
                            val wrongAnswers = tokens.drop(5)
                            questionEntries.add(QuestionFile(index, category, diff, question, answer, wrongAnswers))
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
