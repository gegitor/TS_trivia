package com.ziemowit.ts.trivia.data.model.mappers

import com.ziemowit.ts.trivia.data.model.file.CategoryFile
import com.ziemowit.ts.trivia.data.model.file.DifficultyFile
import com.ziemowit.ts.trivia.data.model.file.LeaderboardEntryFile
import com.ziemowit.ts.trivia.data.model.file.QuestionFile
import com.ziemowit.ts.trivia.domain.model.Category
import com.ziemowit.ts.trivia.domain.model.Difficulty
import com.ziemowit.ts.trivia.domain.model.LeaderboardEntry
import com.ziemowit.ts.trivia.domain.model.PotentialAnswer
import com.ziemowit.ts.trivia.domain.model.Question


/**
 * Created by Ziemowit Pazderski on 3/30/2025.
 * Copyright © 2025 Ziemowit Pazderski. All rights reserved.
 */

fun QuestionFile.toQuestion(): Question {

    val answers: MutableList<PotentialAnswer> = wrongAnswers.map { answerText ->
        PotentialAnswer(answerText, false, false)
    }.toMutableList()

    answers.add(PotentialAnswer(correctAnswer, true, false))
    answers.shuffle()
    return Question(index, difficultyFile.toDifficulty(), question, answers.toList())
}


fun CategoryFile.toCategory(): Category {
    return when (this) {
        CategoryFile.GAME_MECHANICS -> return Category.GAME_MECHANICS
        CategoryFile.CARDS_OPERATION -> return Category.CARDS_OPERATION
        CategoryFile.CARDS_HISTORY -> return Category.CARDS_HISTORY
        CategoryFile.CREATORS -> Category.CREATORS
        CategoryFile.PLAYERS -> Category.PLAYERS
        CategoryFile.GAME_HISTORY -> Category.GAME_HISTORY
        CategoryFile.BOARD -> Category.BOARD
    }
}

fun DifficultyFile.toDifficulty(): Difficulty {
    return when (this) {
        DifficultyFile.EASY -> return Difficulty.EASY
        DifficultyFile.MEDIUM -> return Difficulty.MEDIUM
        DifficultyFile.HARD -> return Difficulty.HARD
        DifficultyFile.WOJTEK -> Difficulty.WOJTEK
    }
}

fun LeaderboardEntryFile.LeaderboardEntry() = LeaderboardEntry(scoreUpThreshold, leaderName)

//fun SoundFile.toSound() : Sound {
//    return when (this) {
//        SoundFile.Ok -> return Sound.Ok
//        SoundFile.Clever -> return Sound.Clever
//        SoundFile.Ambitious -> return Sound.Ambitious
//        SoundFile.HiWojtek -> return Sound.HiWojtek
//        SoundFile.Perfect -> Sound.Perfect
//        SoundFile.ThatSWrong -> Sound.ThatSWrong
//    }
//}