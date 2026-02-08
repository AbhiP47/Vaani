package com.example.appshalavoiceassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appshalavoiceassistant.ui.animations.VoiceWaveformAnimation

@Composable
fun VoiceCallScreen(onEndCall: () -> Unit) {
    // 1. These declarations MUST be inside the function to fix "Unresolved Reference"
    var isMuted by remember { mutableStateOf(false) }
    var lastClickTime by remember { mutableLongStateOf(0L) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF02040A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isMuted) "आवाज बंद है (Muted)" else "AI सहायक सक्रिय है",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 60.dp)
        )

        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            VoiceWaveformAnimation(isMuted = isMuted)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { isMuted = !isMuted },
                modifier = Modifier.size(60.dp).background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(
                    imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                    contentDescription = "Mute",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(40.dp))

            var lastClickTime by remember { mutableLongStateOf(0L) }

            FloatingActionButton(
                modifier = Modifier.size(60.dp),
                containerColor = Color(0xFF9C27B0),
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    // Only allow the click if more than 500ms has passed since the last one
                    if (currentTime - lastClickTime > 500L) {
                        lastClickTime = currentTime
                        onEndCall()
                    }
                }
            )
            {
                Icon(Icons.Default.CallEnd, contentDescription = "End Call")
            }
        }
    }
}