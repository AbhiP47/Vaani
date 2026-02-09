package com.example.appshalavoiceassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appshalavoiceassistant.ui.animations.VoiceWaveformAnimation

@Composable
fun VoiceCallScreen(onEndCall: () -> Unit) {
    var isMuted by remember { mutableStateOf(false) }
    var isOnHold by remember { mutableStateOf(false) }

    // Logic for dynamic captions
    var captionText by remember { mutableStateOf("वाणी आपकी बात सुनने के लिए तैयार है...") }
    var isAiSpeaking by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF02040A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status Heading
        Text(
            text = when {
                isOnHold -> "होल्ड पर है (On Hold)"
                isMuted -> "आवाज बंद है (Muted)"
                else -> "VAANI सक्रिय है"
            },
            color = when {
                isOnHold -> Color.Yellow
                isMuted -> Color.Red
                else -> Color.White
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp)
        )

        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            VoiceWaveformAnimation(isMuted = isMuted, isOnHold = isOnHold)
        }

        // --- DYNAMIC CAPTION BOX ---
        // This box changes based on who is speaking
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp),
            color = Color.White.copy(alpha = 0.07f),
            shape = RoundedCornerShape(20.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Small dot indicator
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(if (isAiSpeaking) Color(0xFF9C27B0) else Color.Cyan, CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (isAiSpeaking) "वाणी (VAANI)" else "आप (You)",
                        color = if (isAiSpeaking) Color(0xFF9C27B0) else Color.Cyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isMuted || isOnHold) "---" else captionText,
                    color = Color.White,
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        // CONTROL ROW
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mute
            IconButton(
                onClick = { isMuted = !isMuted },
                modifier = Modifier.size(56.dp).background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(if (isMuted) Icons.Default.MicOff else Icons.Default.Mic, null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Hold
            IconButton(
                onClick = { isOnHold = !isOnHold },
                modifier = Modifier.size(64.dp).background(if (isOnHold) Color.White.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(if (isOnHold) Icons.Default.PlayArrow else Icons.Default.Pause, null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(24.dp))

            var lastClickTime by remember { mutableLongStateOf(0L) }
            // End Call
            FloatingActionButton(
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - lastClickTime > 500L) {
                        lastClickTime = currentTime
                        onEndCall()
                    }
                },
                containerColor = Color(0xFF9C27B0),
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.Default.CallEnd, null, tint = Color.White)
            }
        }
    }
}