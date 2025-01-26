package com.ziemowit.ts.ui_common.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ziemowit.ts.ui_common.R
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun LoadingContent() {
    // State to track loading progress
    var progress by remember { mutableFloatStateOf(0f) }

    // Update progress dynamically
    LaunchedEffect(Unit) {
        while (true) {
            delay(50) // Update progress every 50ms
            progress = (progress + 0.01f) % 1f // Loop progress between 0 and 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A237E), Color(0xFF4527A0))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Lottie Animation
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
        val progressAnimation by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever, // Loop indefinitely
            speed = 1f
        )

        LottieAnimation(
            composition = composition,
            progress = { progressAnimation },
            modifier = Modifier.size(200.dp)
        )
        AnimatedLoadingText()
    }
}

@Composable
fun AnimatedLoadingText() {
    // State to track the current ellipsis count
    var ellipsisCount by remember { mutableStateOf(0) }

    // Animate the ellipsis count
    LaunchedEffect(Unit) {
        while (true) {
            delay(300) // Update every 300ms
            ellipsisCount = (ellipsisCount + 1) % 4 // Cycle through 0, 1, 2, 3
        }
    }

    // Create the "Loading..." text with animated ellipsis
    val loadingText = "Loading" + ".".repeat(ellipsisCount)

    Text(
        text = loadingText,
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp)
    )
}