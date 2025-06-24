package org.kmpdemo.android.ui

import com.kmpdemo.presentation.PlatformServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.kmpdemo.android.PlatformUiActions

class PlatformServicesImpl : PlatformServices {

    private val _uiEvents = MutableSharedFlow<PlatformUiActions>()
    override val uiEvents: SharedFlow<PlatformUiActions> = _uiEvents

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun showToast(message: String) {
        _uiEvents.tryEmit(PlatformUiActions.ShowToast(message))
    }

    override fun showDialog(title: String, message: String) {
        _uiEvents.tryEmit(PlatformUiActions.ShowDialog(title, message))
    }

    override fun log(message: String) {
        println("ComposeApp log: $message")
    }

    override fun getDeviceLocale(): String {
        return "en"
    }

    override fun startListeningUiEvents(onEvent: (Any) -> Unit) {
        scope.launch {
            uiEvents.collect { event ->
                onEvent(event)
            }
        }
    }
}