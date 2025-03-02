package com.ziemowit.ts.trivia.data.usecases

import com.ziemowit.ts.trivia.data.QuestionRepository
import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.data.model.QuestionInfo
import com.ziemowit.ts.trivia.data.model.toQuestionInfo
import javax.inject.Inject

class GetQuestionsUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : GetQuestionsUseCase() {
    override suspend operator fun invoke(difficulty: Difficulty): List<QuestionInfo> {
        return questionRepository.getQuestions(difficulty).map { it.toQuestionInfo() }
    }
}
