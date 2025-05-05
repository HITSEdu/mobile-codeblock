package hitsedu.mobile_codeblock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import hitsedu.ui.MainScreen
import hitsedu.ui_kit.theme.MobilecodeblockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobilecodeblockTheme {
                MainScreen()
            }
        }
    }
}