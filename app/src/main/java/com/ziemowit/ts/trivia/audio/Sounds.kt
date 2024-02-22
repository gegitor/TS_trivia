package com.ziemowit.ts.trivia.audio

import androidx.annotation.RawRes
import com.ziemowit.ts.trivia.R

sealed class Sound(@RawRes val path: Int) {
    data object Ok : Sound(R.raw.ok)

}