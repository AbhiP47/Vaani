package com.example.appshalavoiceassistant.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import com.example.appshalavoiceassistant.ui.screens.HomeScreen
import com.example.appshalavoiceassistant.ui.screens.SplashScreen
import com.example.appshalavoiceassistant.ui.screens.VoiceCallScreen


object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val VOICE_CALL = "voice_call"
}

// In your NavGraph.kt file
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash", // Make sure this points to your splash screen
        modifier = Modifier.fillMaxSize()
    ) {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("home") {
                    // This removes splash from the backstack so the user can't go "back" to it
                    popUpTo("splash") { inclusive = true }
                }
            })
            }
        composable("home") {
            HomeScreen(onStartCall = {
                navController.navigate("voice_call") {
                    // This is the magic line: it prevents screen piling
                    launchSingleTop = true

                    // Optional: Clears the home screen from the stack so you can't go "back" to it
                    popUpTo("home") { inclusive = false }

                    restoreState = true
                }
            })
        }

        composable("voice_call") {
            VoiceCallScreen(onEndCall = {
                // Clear everything and go back to a fresh Home screen
                navController.popBackStack("home", inclusive = false)
            })
        }
    }
}