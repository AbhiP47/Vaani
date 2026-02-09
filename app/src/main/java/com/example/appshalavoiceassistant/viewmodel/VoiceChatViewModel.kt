package com.example.appshalavoiceassistant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.LiveSession
import com.google.firebase.ai.type.PublicPreviewAPI
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.SpeechConfig
import com.google.firebase.ai.type.Voice
import com.google.firebase.ai.type.generationConfig
import com.google.firebase.ai.type.liveGenerationConfig
import com.google.firebase.app
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



class VoiceChatViewModel : ViewModel() {
    private val _isMuted = MutableStateFlow(false)
    val isMuted = _isMuted.asStateFlow()

    @OptIn(PublicPreviewAPI::class)
    private var liveSession : LiveSession? = null

    // Initialize the model
    @OptIn(PublicPreviewAPI::class)
    private val liveModel = Firebase.ai(backend = GenerativeBackend.googleAI()).liveModel(
        modelName = "gemini-2.5-flash-native-audio-preview-12-2025",
        // Configure the model to respond with audio
        generationConfig = liveGenerationConfig {
            responseModality = ResponseModality.AUDIO
        }
    )

    @OptIn(PublicPreviewAPI::class)
    fun startConversation(){
        viewModelScope.launch {
            try {
                // Establish connection if not already connected
                if (liveSession == null) {
                    liveSession = liveModel.connect()
                }
                liveSession?.startAudioConversation()
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    @OptIn(PublicPreviewAPI::class)
    fun toggleMute(){
        _isMuted.value = !_isMuted.value
    }

    @OptIn(PublicPreviewAPI::class)
    fun stopConversation(){
        viewModelScope.launch {
            liveSession?.stopAudioConversation()
            liveSession = null
            _isMuted.value = false
        }
    }

}