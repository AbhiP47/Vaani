package com.example.appshalavoiceassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
    var isMuted by remember { mutableStateOf(false) }
    var isOnHold by remember { mutableStateOf(false) } // Added hold state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF02040A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when {
                isOnHold -> "होल्ड पर है (On Hold)"
                isMuted -> "आवाज बंद है (Muted)"
                else -> "VAANI सफलतापूर्वक शुरू हो गई है ✅"
            },
            color = when {
                isOnHold -> Color.Yellow
                isMuted -> Color.Red
                else -> Color.White },
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 60.dp)
        )

        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Animation stops moving if on hold or muted
            VoiceWaveformAnimation(
                isMuted = isMuted,
                isOnHold = isOnHold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mute Button
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

            Spacer(modifier = Modifier.width(20.dp))

            // NEW: Hold Button
            IconButton(
                onClick = { isOnHold = !isOnHold },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (isOnHold) Color.White.copy(alpha = 0.4f) else Color.White.copy(alpha = 0.1f),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = if (isOnHold) Icons.Default.PlayArrow else Icons.Default.Pause,
                    contentDescription = "Hold",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            var lastClickTime by remember { mutableLongStateOf(0L) }

            // End Call Button
            FloatingActionButton(
                modifier = Modifier.size(60.dp),
                containerColor = Color(0xFF9C27B0),
                shape = CircleShape,
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - lastClickTime > 500L) {
                        lastClickTime = currentTime
                        onEndCall()
                    }
                }
            )
            {
                Icon(Icons.Default.CallEnd, contentDescription = "End Call", tint = Color.White)
            }
        }
    }
}