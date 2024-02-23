package com.ziemowit.ts.trivia.audio

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

//import me.tatarka.inject.annotations.Inject

class SoundRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) : SoundRepository {
    override fun play(sound: Sound) {
        val mediaPlayer = MediaPlayer.create(context, sound.path)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            Timber.d("Released media player.")
            mediaPlayer.release()
        }
    }

    override fun stopAll() {
    }
}