package com.example.appshalavoiceassistant.navigation

import ActiveChatScreen
import StartScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appshalavoiceassistant.viewmodel.VoiceChatViewModel

@Composable
fun VoiceChatNavigation(){
    val navController = rememberNavController()
    val sharedViewModel : VoiceChatViewModel = viewModel()

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(
                onNavigateToChat = {
                    sharedViewModel.startConversation()
                    navController.navigate("active")
                }
            )
        }

        composable("active") {
            ActiveChatScreen(
                viewModel = sharedViewModel,
                onBack = {
                    sharedViewModel.stopConversation()
                    navController.popBackStack()
                }
            )
        }
    }
}