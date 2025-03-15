package com.ziemowit.ts.trivia.app.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.Difficulty

/**
 * State class representing the data displayed on the Trivia Master home screen.
 */
data class HomeState(
    // User information
    val userName: State<String>,

    // Continue quiz information (null if no quiz to continue)
    val continueQuiz: State<ContinueQuizInfo?>,

    // Recent activity items
    val recentActivities: State<List<ActivityItem>>,

    // Whether loading is in progress
//    val isLoading: Boolean = false,

    // Any error message to display (null if no error)
//    val errorMessage: String? = null
) {
    /**
     * Information about a quiz that can be continued.
     */
    data class ContinueQuizInfo(
        val id: String,
        val title: String,
        val questionsCompleted: Int,
        val totalQuestions: Int,
        val categoryId: String,
        val categoryName: String,
        val difficultyLevel: Difficulty
    )

    /**
     * Represents an activity item in the recent activity feed.
     */
    sealed class ActivityItem(val priority: Int) {
        abstract val id: String
        abstract val timestamp: Long

        /**
         * A challenge from another user.
         */
        data class Challenge(
            override val id: String,
            override val timestamp: Long,
            val fromUserId: String,
            val fromUserName: String,
            val fromUserAvatarUrl: String?,
            val quizName: String,
            val categoryName: String,
            val difficultyLevel: Difficulty,
            val expiresAt: Long?
        ) : ActivityItem(1)

        /**
         * A score comparison with another user.
         */
        data class ScoreComparison(
            override val id: String,
            override val timestamp: Long,
            val otherUserId: String,
            val otherUserName: String,
            val otherUserAvatarUrl: String?,
            val quizName: String,
            val categoryName: String,
            val yourScore: Float, // percentage
            val theirScore: Float, // percentage
            val youWon: Boolean
        ) : ActivityItem(2)

        /**
         * A personal achievement.
         */
        data class Achievement(
            override val id: String,
            override val timestamp: Long,
            val title: String,
            val description: String,
            val iconResId: Int,
            val quizName: String,
            val categoryName: String?
        ) : ActivityItem(3)
    }

    companion object {
        fun stub() = HomeState(
            userName = mutableStateOf("Alex"),
            continueQuiz = mutableStateOf(
                ContinueQuizInfo(
                    id = "quiz123",
                    title = "Science Quiz",
                    questionsCompleted = 7,
                    totalQuestions = 10,
                    categoryId = "science",
                    categoryName = "Science",
                    difficultyLevel = Difficulty.MEDIUM
                )
            ),
            recentActivities = mutableStateOf(
                listOf(
                    ActivityItem.Challenge(
                        id = "challenge1",
                        timestamp = System.currentTimeMillis() - 3600000, // 1 hour ago
                        fromUserId = "user456",
                        fromUserName = "Mike",
                        fromUserAvatarUrl = null,
                        quizName = "Sports Quiz",
                        categoryName = "Sports",
                        difficultyLevel = Difficulty.HARD,
                        expiresAt = System.currentTimeMillis() + 86400000 // expires in 24 hours
                    ),
                    ActivityItem.ScoreComparison(
                        id = "comparison1",
                        timestamp = System.currentTimeMillis() - 7200000, // 2 hours ago
                        otherUserId = "user789",
                        otherUserName = "Sarah",
                        otherUserAvatarUrl = null,
                        quizName = "Music Quiz",
                        categoryName = "Music",
                        yourScore = 95f,
                        theirScore = 87f,
                        youWon = true
                    ),
                    ActivityItem.Achievement(
                        id = "achievement1",
                        timestamp = System.currentTimeMillis() - 10800000, // 3 hours ago
                        title = "New personal best!",
                        description = "You got all questions correct!",
                        iconResId = R.drawable.round_launcher,//ic_achievement,
                        quizName = "History Quiz",
                        categoryName = "History"
                    ),
                )
            )
        )
    }
}
