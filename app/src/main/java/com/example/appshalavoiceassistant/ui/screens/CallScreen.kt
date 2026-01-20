package com.example.appshalavoiceassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appshalavoiceassistant.ui.animations.VoiceWaveformAnimation
import com.example.appshalavoiceassitant.R

@Composable
fun CallScreen(onEndCall: () -> Unit) {
    var isMuted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("AI सहायक सक्रिय है", color = Color.White, modifier = Modifier.padding(top = 50.dp))

        Spacer(modifier = Modifier.weight(1f))

        // THE ANIMATION LIVES ONLY HERE
        VoiceWaveformAnimation(isMuted = isMuted)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Mute Button
            IconButton(onClick = { isMuted = !isMuted }) {
                Icon(
                    painter = painterResource(id = if (isMuted) R.drawable.end_call else R.drawable.mic),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            // End Call Button
            FloatingActionButton(onClick = onEndCall, containerColor = Color.Red) {
                Icon(Icons.Default.CallEnd, contentDescription = null, tint = Color.White)
            }
        }
    }
}