package com.ziemowit.ts.trivia.domain.repository

import android.content.Context
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.file.LeaderboardEntryFile
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader

interface LeaderRepository {
    fun getLeaderboard(userScore: Float): List<LeaderboardEntryFile>
}

class LeaderRepositoryImpl(private val context: Context) : LeaderRepository {

    override fun getLeaderboard(userScore: Float): List<LeaderboardEntryFile> {
        Timber.d("getLeaderboard userScore: $userScore")
        val leaderboard = mutableListOf<LeaderboardEntryFile>()
        val inputStream = context.resources.openRawResource(R.raw.leaders)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val percentageScore = userScore * 100

        // Skip header line
        reader.readLine()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let {
                val tokens = it.split(",")
                if (tokens.size == 2) {
                    val score = tokens[0].trim().toIntOrNull()
                    val leader = tokens[1].trim()
                    if (score != null && score <= percentageScore) {
                        leaderboard.add(LeaderboardEntryFile(score, leader))
                    }
                }
            }
        }
        reader.close()
        return leaderboard.sortedByDescending { it.scoreUpThreshold }
    }
}
