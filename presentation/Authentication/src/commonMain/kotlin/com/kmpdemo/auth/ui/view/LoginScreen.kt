package com.kmpdemo.auth.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kmpdemo.auth.ui.viewmodel.AuthUiEffect
import com.kmpdemo.auth.ui.viewmodel.AuthUiEvent
import com.kmpdemo.auth.ui.viewmodel.AuthUiState
import com.kmpdemo.presentation.PlatformServices
import com.kmpdemo.presentation.di.rememberKoinInstance
import com.kmpdemo.presentation.ui.composables.AppButton
import com.kmpdemo.presentation.ui.composables.AppOutlinedTextField
import com.kmpdemo.presentation.ui.composables.AppText
import com.kmpdemo.presentation.ui.theme.AppTheme
import com.kmpdemo.presentation.utils.AppRoute
import com.kmpdemo.resources.AppImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

//private const val DELAY_TO_KEYBOARD: Long = 300

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: AuthUiState,
    onEvent: (AuthUiEvent) -> Unit,
    uiEffect: Flow<AuthUiEffect>,
    onNavigateToScreen: (AppRoute) -> Unit
) {

    val platformServices: PlatformServices = rememberKoinInstance()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValidMobile = uiState.isValidMobile

    LaunchedEffect(Unit) {
        uiEffect.collectLatest {
            when (it) {
                is AuthUiEffect.ShowToast -> {
                    platformServices.showToast(it.message)
                }

                is AuthUiEffect.NavigateToScreen -> onNavigateToScreen(it.route)
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(25.dp)
                .windowInsetsPadding(WindowInsets.ime)
        ) {
            Image(
                painter = painterResource(AppImages.LOGIN_BCKG),
                contentDescription = "Login background",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                AppText(
                    text = "Enter your phone number",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = modifier.height(8.dp))
                AppText(
                    text = "You will receive a 4 digit code for phone number verification.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.height(20.dp))
                AppOutlinedTextField(
                    modifier = modifier.fillMaxWidth()
                        .focusRequester(focusRequester)
                        .clickable {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        },
                    value = uiState.mobile,
                    singleLine = true,
                    maxLines = 1,
                    onValueChange = {
                        if (it.length <= 10) {
                            onEvent(AuthUiEvent.MobileChanged(it))
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if (isValidMobile) onEvent(AuthUiEvent.MobileRegistration)
                        }
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = "Phone Number",
                    shape = MaterialTheme.shapes.medium,
                )
                Spacer(modifier = modifier.height(20.dp))
                AppButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .alpha(if (isValidMobile) 1f else 0.6f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        keyboardController?.hide()
                        onEvent(AuthUiEvent.MobileRegistration)
                    },
                    contentPadding = PaddingValues(15.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(modifier.size(18.dp), strokeWidth = 2.dp)
                    } else {
                        AppText(
                            text = "Send OTP",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Spacer(modifier = modifier.height(15.dp))
            }
        }
    }

//    LaunchedEffect(focusRequester) {
//        delay(DELAY_TO_KEYBOARD)
//        focusRequester.requestFocus()
//        keyboardController?.show()
//    }
}


@Preview()
@Composable
internal fun LoginScreenPreview() {
    // A sample UI state with a 4-digit OTP
    val uiState = AuthUiState(
        isLoading = false,
        mobile = "1234567890"
    )

    // Empty handlers for the preview (no action required here)
    val onEvent: (AuthUiEvent) -> Unit = {}
    val uiEffect: Flow<AuthUiEffect> = flowOf() // Empty flow for the effect handler
    val onNavigateToScreen: (AppRoute) -> Unit = {}

    // Rendering the VerifyOtpScreen
    AppTheme {
        LoginScreen(
            uiState = uiState,
            onEvent = onEvent,
            uiEffect = uiEffect,
            onNavigateToScreen = onNavigateToScreen
        )
    }
}

