package com.ziemowit.ts.trivia.audio

import androidx.annotation.RawRes
import com.ziemowit.ts.trivia.R

sealed class Sound(@RawRes val path: Int) {
    data object Ok : Sound(R.raw.ok)
    data object Clever : Sound(R.raw.ok)
    data object Ambitious : Sound(R.raw.ok)
    data object HiWojtek : Sound(R.raw.ok)
    data object ThatSWrong : Sound(R.raw.ok)
    data object Perfect : Sound(R.raw.ok)

}