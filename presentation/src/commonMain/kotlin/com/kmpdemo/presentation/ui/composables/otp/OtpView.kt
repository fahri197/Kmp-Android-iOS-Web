package com.kmpdemo.presentation.ui.composables.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun OtpView(
    modifier: Modifier = Modifier,
    otpLength: Int = 4,
    otpValue: String,
    onOtpChange: (String) -> Unit
) {
    val focusRequesters = List(otpLength) { remember { FocusRequester() } }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(otpLength) { index ->
            val digit = otpValue.getOrNull(index)?.toString() ?: ""

            OtpTextField(
                value = digit,
                onValueChange = { input ->
                    if (input.length <= 1 && input.all { it.isDigit() }) {
                        val otpChars = otpValue.padEnd(otpLength).toCharArray()

                        otpChars[index] = input.getOrNull(0) ?: ' '
                        val newOtp = otpChars.concatToString().trimEnd()

                        onOtpChange(newOtp)

                        // Move to next field
                        if (input.isNotEmpty() && index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        } else if (input.isNotEmpty() && newOtp.length == otpLength) {
                            keyboardController?.hide()
                        }
                    }
                },
                modifier = Modifier
                    .width(60.dp)
                    .aspectRatio(1f)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent {
                        // Handle backspace
                        if (it.key == Key.Backspace) {
                            val otpChars = otpValue.padEnd(otpLength).toCharArray()

                            if (digit.isNotEmpty()) {
                                // Clear current digit
                                otpChars[index] = ' '
                                onOtpChange(otpChars.concatToString().trimEnd())
                            } else if (index > 0) {
                                // Move focus back and clear previous digit
                                otpChars[index - 1] = ' '
                                onOtpChange(otpChars.concatToString().trimEnd())
                                focusRequesters[index - 1].requestFocus()
                            }

                            true
                        }else false
                    }
            )
        }
    }

    // Focus on first pin if needed
//    LaunchedEffect(Unit) {
//        focusRequesters[0].requestFocus()
//    }
}
