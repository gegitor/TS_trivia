package com.ziemowit.ts.trivia.data

import android.content.Context
import com.ziemowit.ts.trivia.R
import com.ziemowit.ts.trivia.data.model.LeaderboardEntry
import java.io.BufferedReader
import java.io.InputStreamReader

interface LeaderRepository {
    fun getLeaderboard(userScore: Int): List<LeaderboardEntry>
}

class LeaderRepositoryImpl(private val context: Context) : LeaderRepository {

    override fun getLeaderboard(userScore: Int): List<LeaderboardEntry> {
        val leaderboard = mutableListOf<LeaderboardEntry>()
        val inputStream = context.resources.openRawResource(R.raw.leaders)
        val reader = BufferedReader(InputStreamReader(inputStream))

        // Skip header line
        reader.readLine()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let {
                val tokens = it.split(",")
                if (tokens.size == 2) {
                    val score = tokens[0].trim().toIntOrNull()
                    val leader = tokens[1].trim()
                    if (score != null && score <= userScore) {
                        leaderboard.add(LeaderboardEntry(score, leader))
                    }
                }
            }
        }
        reader.close()
        return leaderboard.sortedByDescending { it.scoreUpThreshold }
    }
}
