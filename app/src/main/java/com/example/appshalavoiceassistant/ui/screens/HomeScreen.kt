package com.example.appshalavoiceassistant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appshalavoiceassitant.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(onStartCall: () -> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val micPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.RECORD_AUDIO
    )

    // Automatically navigate if permission is granted while on this screen
    LaunchedEffect(micPermissionState.status.isGranted) {
        if (micPermissionState.status.isGranted) {
            onStartCall()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF02040A))) {
        Image(
            painter = painterResource(id = R.drawable.homescreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        Column(
            modifier = Modifier.align(Alignment.Center).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "सहायता के लिए बटन दबाएं",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            GlowEffect(color = Color(0xFF9C27B0))

            Button(
                onClick = {
                    when {
                        micPermissionState.status.isGranted -> {
                            onStartCall()
                        }
                        // If permanently denied, take them to settings
                        micPermissionState.status.shouldShowRationale.not() && !micPermissionState.status.isGranted -> {
                            val intent = android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = android.net.Uri.fromParts("package", context.packageName, null)
                            }
                            context.startActivity(intent)
                        }
                        else -> {
                            micPermissionState.launchPermissionRequest()
                        }
                    }
                },
                modifier = Modifier.width(220.dp).height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
            ) {
                val buttonText = when {
                    micPermissionState.status.isGranted -> "शुरू करें"
                    micPermissionState.status.shouldShowRationale.not() -> "सेटिंग्स खोलें"
                    else -> "अनुमति दें"
                }
                Text(buttonText, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Composable
fun GlowEffect(color: Color) {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 80.dp)
            .blur(radius = 25.dp) // Softens the glow
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        color.copy(alpha = 0.6f),
                        Color.Transparent
                    )
                ),
                shape = RoundedCornerShape(40.dp)
            )
    )
}