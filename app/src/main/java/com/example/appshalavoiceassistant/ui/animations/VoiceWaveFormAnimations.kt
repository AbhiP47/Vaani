package com.example.appshalavoiceassistant.ui.animations

import com.example.appshalavoiceassitant.R
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun VoiceWaveformAnimation(
    isMuted: Boolean,
    amplitude: Float = 0f
) {
    val waveComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.voice_wave))
    val muteComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.mute_line))

    val composition = if (isMuted) muteComposition else waveComposition
    val dynamicSpeed = if (isMuted) 1f else 0.5f + (amplitude * 2f)

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = dynamicSpeed,
        modifier = Modifier.size(300.dp)
    )
}