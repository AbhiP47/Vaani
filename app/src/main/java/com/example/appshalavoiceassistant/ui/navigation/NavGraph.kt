package com.example.appshalavoiceassistant.ui.navigation

import androidx.compose.runtime.Composable
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

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }
        composable(Routes.HOME) {
            HomeScreen(onStartCall = { navController.navigate(Routes.VOICE_CALL) })
        }
        composable("voice_call") {
            VoiceCallScreen(onEndCall = {
                navController.popBackStack("home", inclusive = false) })
        }
    }
}