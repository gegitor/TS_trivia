package com.ziemowit.ts.trivia.domain.repository

import android.content.Context
import android.media.MediaPlayer
import com.ziemowit.ts.trivia.domain.model.Sound
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface SoundRepository {
    fun play(sound: Sound)

    fun stopAll()
}

class SoundRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) : SoundRepository {
    private var currentMediaPlayer: MediaPlayer? = null

    override fun play(sound: Sound) {
        stopAll() // Stop any currently playing sound before starting a new one

        val mediaPlayer = MediaPlayer.create(context, sound.path)
        currentMediaPlayer = mediaPlayer
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            currentMediaPlayer = null
            mediaPlayer.release()
        }
    }

    override fun stopAll() {
        currentMediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            currentMediaPlayer = null
            it.release()
        }
    }
}
