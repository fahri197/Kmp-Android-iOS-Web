package org.kmpdemo.android

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.kmpdemo.presentation.PlatformServices
import com.kmpdemo.presentation.PlatformUiEvent.ShowDialog
import com.kmpdemo.presentation.PlatformUiEvent.ShowToast
import com.kmpdemo.presentation.di.rememberKoinInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val platformServices = rememberKoinInstance<PlatformServices>()
            LaunchedEffect(Unit) {
                platformServices.uiEvents.collect { event ->
                    when (event) {
                        is ShowToast -> showToast(event.message)
                        is ShowDialog -> showDialog(event.title, event.message)
                    }
                }
            }

            App()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
