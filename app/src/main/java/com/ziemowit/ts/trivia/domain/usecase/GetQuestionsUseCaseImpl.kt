package com.ziemowit.ts.trivia.domain.usecase

import com.ziemowit.ts.trivia.data.model.mappers.toQuestion
import com.ziemowit.ts.trivia.domain.model.Difficulty
import com.ziemowit.ts.trivia.domain.model.Question
import com.ziemowit.ts.trivia.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionsUseCaseImpl @Inject constructor(
    private val questionRepository: QuestionRepository
) : GetQuestionsUseCase() {
    override suspend operator fun invoke(difficulty: Difficulty): List<Question> {
        return questionRepository.getQuestions(difficulty).map { it.toQuestion() }
    }
}
