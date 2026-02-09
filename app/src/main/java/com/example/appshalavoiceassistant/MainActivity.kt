<<<<<<< HEAD
package com.example.appshalavoiceassitant
=======
package com.example.appshalavoiceassistant
>>>>>>> firebaseAIIntegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.appshalavoiceassistant.ui.navigation.NavGraph
import com.example.appshalavoiceassistant.ui.theme.AppShalaVoiceAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppShalaVoiceAssistantTheme {
                NavGraph()
=======
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.appshalavoiceassistant.navigation.VoiceChatNavigation // Ensure this import matches your file structure
import com.example.appshalavoiceassistant.ui.theme.AppshalaVoiceAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for a modern look
        enableEdgeToEdge()

        setContent {
            AppshalaVoiceAssistantTheme {
                // Use Scaffold to handle system bars and edge-to-edge padding
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Set the navigation container as the root content
                    // Applying innerPadding ensures the UI doesn't overlap with the status/nav bars
                    androidx.compose.foundation.layout.Box(modifier = Modifier.padding(innerPadding)) {
                        VoiceChatNavigation()
                    }
                }
>>>>>>> firebaseAIIntegration
            }
        }
    }
}