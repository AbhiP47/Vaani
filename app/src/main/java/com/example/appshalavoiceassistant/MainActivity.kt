package com.example.appshalavoiceassitant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            }
        }
    }
}