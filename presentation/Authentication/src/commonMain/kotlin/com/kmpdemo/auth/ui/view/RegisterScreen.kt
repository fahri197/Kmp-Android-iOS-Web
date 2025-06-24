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
import com.kmpdemo.presentation.utils.extensions.capitalizeWords
import com.kmpdemo.resources.AppImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(
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
    val isValidEmail = uiState.isValidEmail
    val isValidName = uiState.isValidName

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
                    text = "Create an Account",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = modifier.height(8.dp))
                AppText(
                    text = "Please enter your name and email address to create your account and continue.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.height(20.dp))
                AppOutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .clickable {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        },
                    singleLine = true,
                    maxLines = 1,
                    value = uiState.name,
                    onValueChange = { onEvent(AuthUiEvent.NameChanged(it.capitalizeWords())) },
                    label = "Full name",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = MaterialTheme.shapes.medium,
                )
                Spacer(modifier = modifier.height(10.dp))

                AppOutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .clickable {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        },
                    singleLine = true,
                    maxLines = 1,
                    value = uiState.email,
                    onValueChange = { onEvent(AuthUiEvent.EmailChanged(it)) },
                    label = "Email address",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if (isValidMobile && isValidEmail && isValidName) onEvent(AuthUiEvent.Register)
                        }
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = MaterialTheme.shapes.medium,
                )
                Spacer(modifier = modifier.height(25.dp))

                AppButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .alpha(if (isValidMobile && isValidEmail && isValidName) 1f else 0.6f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        keyboardController?.hide()
                        onEvent(AuthUiEvent.Register)
                    },
                    contentPadding = PaddingValues(15.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(modifier.size(18.dp), strokeWidth = 2.dp)
                    } else {
                        AppText(
                            text = "Register",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Spacer(modifier = modifier.height(15.dp))
            }
        }
    }
}

@Preview()
@Composable
internal fun RegisterScreenPreview() {

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
        RegisterScreen(
            uiState = uiState,
            onEvent = onEvent,
            uiEffect = uiEffect,
            onNavigateToScreen = onNavigateToScreen
        )
    }
}