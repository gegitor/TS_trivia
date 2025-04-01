package com.ziemowit.ts.trivia.domain.usecase

import com.ziemowit.ts.trivia.domain.model.Difficulty
import com.ziemowit.ts.trivia.domain.model.Question

abstract class GetQuestionsUseCase {
    abstract suspend operator fun invoke(difficulty: Difficulty): List<Question>
}
