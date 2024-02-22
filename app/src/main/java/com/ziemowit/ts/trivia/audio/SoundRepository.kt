package com.ziemowit.ts.trivia.audio

interface SoundRepository {
    fun play(sound: Sound)
//    fun stop(sound: Sound)
    fun stopAll()
}