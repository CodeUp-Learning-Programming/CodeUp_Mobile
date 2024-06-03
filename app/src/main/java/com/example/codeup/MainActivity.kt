package com.example.codeup

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.codeup.ui.composables.componentes.MyNotification
import com.example.codeup.ui.screens.Login
import com.example.codeup.ui.theme.CodeupTheme


class MainActivity : ComponentActivity() {
    private val REQUEST_CODE = 1001
    // Register the permissions callback, which handles the user's response to the system permissions dialog.
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted. Continue with the notification.
            val myNotification = MyNotification(this, "Título da Notificação", "Mensagem da Notificação")
            myNotification.fireNotification()
        } else {
            // Permission is denied. Handle the denial.
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                0, 0
            ),
            navigationBarStyle = SystemBarStyle.light(
                0, 0
            )
        )

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(13, 13, 13)
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
                        }
                    }

               Login()
//                    MyApp()
                }
            }
        }
    }
}

//
//data class WordItem(val id: UUID, val word: String)
//
//@Composable
//fun MyApp() {
//    var selectedWords by remember { mutableStateOf(listOf<WordItem>()) }
//    val availableWords = listOf(
//        WordItem(UUID.randomUUID(), "Kotlin"),
//        WordItem(UUID.randomUUID(), "Compose"),
//        WordItem(UUID.randomUUID(), "Jetpack"),
//        WordItem(UUID.randomUUID(), "Android"),
//        WordItem(UUID.randomUUID(), "Compose"), // Repetida
//        WordItem(UUID.randomUUID(), "Kotlin")  // Repetida
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("Available Words", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
//
//        WordList(
//            words = availableWords,
//            selectedWords = selectedWords,
//            onWordClick = { wordItem ->
//                if (selectedWords.any { it.id == wordItem.id }) {
//                    selectedWords = selectedWords.filter { it.id != wordItem.id }
//                } else {
//                    selectedWords = selectedWords + wordItem
//                }
//            }
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Text("Selected Words", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
//
//        SelectedWordsArea(selectedWords)
//    }
//}
//
//@Composable
//fun WordList(words: List<WordItem>, selectedWords: List<WordItem>, onWordClick: (WordItem) -> Unit) {
//    Column {
//        words.forEach { wordItem ->
//            val isSelected = selectedWords.any { it.id == wordItem.id }
//            Text(
//                text = wordItem.word,
//                fontSize = 18.sp,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .background(if (isSelected) Color.Green else Color.LightGray)
//                    .padding(8.dp)
//                    .clickable { onWordClick(wordItem) }
//            )
//        }
//    }
//}
//
//@Composable
//fun SelectedWordsArea(words: List<WordItem>) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.Gray)
//            .padding(16.dp)
//    ) {
//        words.forEach { wordItem ->
//            Text(text = wordItem.word, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
//        }
//    }
//}