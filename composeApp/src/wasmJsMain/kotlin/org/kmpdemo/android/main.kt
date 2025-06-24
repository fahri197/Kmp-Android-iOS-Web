package org.kmpdemo.android

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.kmpdemo.presentation.PlatformServices
import com.kmpdemo.presentation.di.rememberKoinInstance
import kotlinx.browser.document
import kotlinx.browser.window
import org.kmpdemo.android.PlatformUiActions.ShowDialog
import org.kmpdemo.android.PlatformUiActions.ShowToast
import org.kmpdemo.android.startup.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    initKoin()

    ComposeViewport(document.body!!) {

        val platformServices = rememberKoinInstance<PlatformServices>()

        LaunchedEffect(Unit) {
            platformServices.uiEvents.collect { event ->
                when (event) {
                    is ShowToast -> window.alert(event.message)
                    is ShowDialog -> window.alert("${event.title}\n\n${event.message}")
                }
            }
        }

        App()
    }
}