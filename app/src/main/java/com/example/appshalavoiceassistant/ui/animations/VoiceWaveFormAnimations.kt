package com.example.appshalavoiceassistant.ui.animations

import com.example.appshalavoiceassitant.R
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun VoiceWaveformAnimation(isMuted: Boolean) { // This parameter is the "Switch"
    val waveComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.voice_wave))
    val muteComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.mute_line))

    // Switch logic: if isMuted is true, use muteComposition. Otherwise, use wave.
    val composition = if (isMuted) muteComposition else waveComposition

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(300.dp)
    )
}