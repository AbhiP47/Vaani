package com.example.appshalavoiceassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            }
        }
    }
}