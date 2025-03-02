package com.ziemowit.ts.trivia.data.usecases

import com.ziemowit.ts.trivia.data.model.Difficulty
import com.ziemowit.ts.trivia.data.model.QuestionInfo

abstract class GetQuestionsUseCase {
    abstract suspend operator fun invoke(difficulty: Difficulty): List<QuestionInfo>
}
