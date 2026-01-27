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
    var isMuted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF02040A)), // डार्क बैकग्राउंड
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ऊपरी हिस्सा: स्टेटस टेक्स्ट
        Text(
            text = if (isMuted) "आवाज बंद है (Muted)" else "AI सहायक सक्रिय है",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 60.dp)
        )

        // बीच का हिस्सा: वेवफॉर्म एनीमेशन
        Box(
            modifier = Modifier
                .weight(1f) // यह स्क्रीन के बीच की जगह को कवर करेगा
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            VoiceWaveformAnimation(isMuted = isMuted)
        }

        // निचला हिस्सा: कॉल कंट्रोल बटन्स
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // म्यूट बटन
            IconButton(
                onClick = { isMuted = !isMuted },
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(
                    imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                    contentDescription = "Mute",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(40.dp))

            // कॉल कट बटन
            FloatingActionButton(
                onClick = { onEndCall() }, // यहाँ से आप वापस होम पर जाएंगे
                containerColor = Color.Red,
                shape = CircleShape,
                modifier = Modifier.size(70.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CallEnd,
                    contentDescription = "End Call",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}