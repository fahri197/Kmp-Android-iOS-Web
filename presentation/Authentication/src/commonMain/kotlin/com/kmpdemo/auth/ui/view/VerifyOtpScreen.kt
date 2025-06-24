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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.kmpdemo.auth.ui.viewmodel.AuthUiEffect
import com.kmpdemo.auth.ui.viewmodel.AuthUiEvent
import com.kmpdemo.auth.ui.viewmodel.AuthUiState
import com.kmpdemo.presentation.PlatformServices
import com.kmpdemo.presentation.di.rememberKoinInstance
import com.kmpdemo.presentation.ui.composables.AppButton
import com.kmpdemo.presentation.ui.composables.AppText
import com.kmpdemo.presentation.ui.composables.otp.OtpView
import com.kmpdemo.presentation.ui.theme.AppTheme
import com.kmpdemo.presentation.utils.AppRoute
import com.kmpdemo.resources.AppImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun VerifyOtpScreen(
    modifier: Modifier = Modifier,
    uiState: AuthUiState,
    onEvent: (AuthUiEvent) -> Unit,
    uiEffect: Flow<AuthUiEffect>,
    onNavigateToScreen: (AppRoute) -> Unit
) {

    val platformServices: PlatformServices = rememberKoinInstance()

    val isValidOTP = uiState.isValidOTP
    val mobile = uiState.mobile

    LaunchedEffect(Unit) {
        uiEffect.collectLatest {
            when (it) {
                is AuthUiEffect.ShowToast -> {
                    platformServices.showToast(it.message)
                }

                is AuthUiEffect.NavigateToScreen -> {
                    onNavigateToScreen(it.route)
                }
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
                painter = painterResource(AppImages.OTP_BCKG),
                contentDescription = "Otp background",
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
                    text = "Enter your code",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = modifier.height(8.dp))
                AppText(
                    text = buildAnnotatedString {
                        append("Please enter the 4 digit verification code sent to ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("+91-"+mobile)
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.height(25.dp))

                // OTP Input Field
                OtpView(
                    otpValue = uiState.otp,
                    onOtpChange = { onEvent(AuthUiEvent.OtpChanged(it)) }
                )
                Spacer(modifier = modifier.height(25.dp))

                // Verify Button
                AppButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .alpha(if (isValidOTP) 1f else 0.6f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = { onEvent(AuthUiEvent.VerifyOtp) },
                    contentPadding = PaddingValues(15.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(modifier.size(18.dp), strokeWidth = 2.dp)
                    } else {
                        AppText(
                            text = "Verify OTP",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = modifier.height(5.dp))

                // Resend OTP Button
                AppText(
                    text = "Resend OTP",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .clickable(enabled = !uiState.isLoading) {
                            onEvent(AuthUiEvent.ResendOtp)
                        }
                )

            }
        }

    }
}


@Preview()
@Composable
internal fun VerifyOtpScreenPreview() {
    // A sample UI state with a 4-digit OTP
    val uiState = AuthUiState(
        otp = "1234",
        isLoading = false,
        mobile = "1234567890",
        isOtpSent = true
    )

    // Empty handlers for the preview (no action required here)
    val onEvent: (AuthUiEvent) -> Unit = {}
    val uiEffect: Flow<AuthUiEffect> = flowOf() // Empty flow for the effect handler
    val onNavigateToScreen: (AppRoute) -> Unit = {}

    // Rendering the VerifyOtpScreen
    AppTheme {
        VerifyOtpScreen(
            uiState = uiState,
            onEvent = onEvent,
            uiEffect = uiEffect,
            onNavigateToScreen = onNavigateToScreen
        )
    }
}