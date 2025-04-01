package com.ziemowit.ts.trivia.domain.repository

import android.content.SharedPreferences

interface PreferencesRepository {
    fun getIsSecretDifficultyVisible(): Boolean
    fun setIsSecretDifficultyVisible(visible: Boolean)

    fun getUserName(): String? //TODO - database?
    fun setUserName(name: String)
}

class PreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences) : PreferencesRepository {
    private val PREF_HIDDEN_DIFFICULTY = "PREF_HIDDEN_DIFFICULTY"
    private val PREF_USER_NAME = "PREF_USER_NAME"

    override fun getIsSecretDifficultyVisible() =
        sharedPreferences.getBoolean(PREF_HIDDEN_DIFFICULTY, false)

    override fun setIsSecretDifficultyVisible(visible: Boolean) =
        sharedPreferences.edit().putBoolean(PREF_HIDDEN_DIFFICULTY, visible).apply()

    override fun getUserName(): String?  =
        sharedPreferences.getString(PREF_USER_NAME, null)

    override fun setUserName(name: String) =
        sharedPreferences.edit().putString(PREF_USER_NAME, name).apply()
}
