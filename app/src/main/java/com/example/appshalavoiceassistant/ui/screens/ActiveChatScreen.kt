import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appshalavoiceassistant.viewmodel.VoiceChatViewModel

@Composable
fun ActiveChatContent(
    isMuted: Boolean,
    onMuteToggle: () -> Unit,
    onEnd: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("VAANI is Listening...", style = MaterialTheme.typography.headlineSmall)

        // Pulsating UI logic

        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            FloatingActionButton(
                onClick = onMuteToggle,
                containerColor = if (isMuted) Color.Red else Color.Gray
            ) {
                Icon(if (isMuted) Icons.Default.MicOff else Icons.Default.Mic, "Mute")
            }

            FloatingActionButton(onClick = onEnd, containerColor = Color.Red) {
                Icon(Icons.Default.CallEnd, "End", tint = Color.White)
            }
        }
    }
}


@Composable
fun ActiveChatScreen(viewModel: VoiceChatViewModel, onBack: () -> Unit) {
    val isMuted by viewModel.isMuted.collectAsState()

    // Pass the state from the ViewModel down to the Content
    ActiveChatContent(
        isMuted = isMuted,
        onMuteToggle = { viewModel.toggleMute() },
        onEnd = onBack
    )
}

@Preview(showBackground = true, name = "Active Chat - Talking")
@Composable
fun ActiveChatTalkingPreview() {
    ActiveChatContent(isMuted = false, onMuteToggle = {}, onEnd = {})
}

@Preview(showBackground = true, name = "Active Chat - Muted")
@Composable
fun ActiveChatMutedPreview() {
    ActiveChatContent(isMuted = true, onMuteToggle = {}, onEnd = {})
}