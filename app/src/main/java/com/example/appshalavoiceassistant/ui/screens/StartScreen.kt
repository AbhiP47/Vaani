import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StartScreen(onNavigateToChat: () -> Unit) {
    val micPermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Appshala Vaani", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (micPermissionState.status.isGranted) {
                        onNavigateToChat()
                    } else {
                        micPermissionState.launchPermissionRequest()
                    }
                },
                modifier = Modifier.size(160.dp),
                shape = CircleShape
            ) {
                Text(
                    text = if (micPermissionState.status.isGranted) "Start Chat" else "Grant Mic",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }

}
@Composable
fun StartScreenContent(isPermissionGranted: Boolean, onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onButtonClick, shape = CircleShape, modifier = Modifier.size(160.dp)) {
            Text(if (isPermissionGranted) "Start Chat" else "Grant Mic")
        }
    }
}

// --- The Preview ---
@Preview(showBackground = true, name = "Start Screen - No Permission")
@Composable
fun StartScreenPermissionPreview() {
    StartScreenContent(isPermissionGranted = false, onButtonClick = {})
}