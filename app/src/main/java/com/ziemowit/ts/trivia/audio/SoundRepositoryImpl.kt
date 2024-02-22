package com.ziemowit.ts.trivia.audio

import android.content.Context
import android.media.MediaPlayer
import me.tatarka.inject.annotations.Inject

class SoundRepositoryImpl @Inject constructor(private val context: Context) : SoundRepository {
    override fun play(sound: Sound) {
        val mediaPlayer = MediaPlayer.create(context, sound.path)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { mediaPlayer.release() }
    }

//    override fun stop(sound: Sound) {
//        TODO("Not yet implemented")
//    }

    override fun stopAll() {
        TODO("Not yet implemented")
    }
}