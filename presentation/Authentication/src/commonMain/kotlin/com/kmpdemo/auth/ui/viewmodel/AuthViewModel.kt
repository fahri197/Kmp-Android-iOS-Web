package com.kmpdemo.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmpdemo.domain.entities.auth.RegisterUserRequest
import com.kmpdemo.local.pref.KeyValueStorage
import com.kmpdemo.presentation.utils.AppRoute
import com.kmpdemo.presentation.utils.PrefKeys
import com.kmpdemo.usecase.auth.AuthUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCases: AuthUseCases,
    private val preferences: KeyValueStorage
) : ViewModel() {

    private var isLoggedIn: Boolean
        get() = preferences.getBoolean(PrefKeys.IS_LOGGED_IN, false)
        set(value) = preferences.putBoolean(PrefKeys.IS_LOGGED_IN, value)

    private val _authUiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState>
        get() = _authUiState.asStateFlow()

    private val _authUiEffect = MutableSharedFlow<AuthUiEffect>()
    val authUiEffect: SharedFlow<AuthUiEffect> = _authUiEffect.asSharedFlow()

    fun onEventChange(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.MobileChanged -> {
                _authUiState.update { it.copy(mobile = event.mobile) }
            }

            is AuthUiEvent.NameChanged -> {
                _authUiState.update { it.copy(name = event.name) }
            }

            is AuthUiEvent.EmailChanged -> {
                _authUiState.update { it.copy(email = event.email) }
            }

            is AuthUiEvent.OtpChanged -> {
                _authUiState.update { it.copy(otp = event.otp) }
            }

            AuthUiEvent.MobileRegistration -> checkMobileRegistration()

            AuthUiEvent.RequestOtp -> sendOtp()

            AuthUiEvent.ResendOtp -> sendOtp(isResend = true)

            AuthUiEvent.VerifyOtp -> verifyOtp()

            AuthUiEvent.Register -> registerUser()
        }
    }

    private fun checkMobileRegistration() {
        val currentState = _authUiState.value
        viewModelScope.launch {
            if (currentState.isValidMobile.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Invalid phone number"))
                return@launch
            }

            _authUiState.update { it.copy(isLoading = true) }

            authUseCases.mobileRegisteredUseCase.execute(currentState.mobile)
                .onSuccess { response ->
                    _authUiState.update { it.copy(isNewUser = (response.isRegistered ?: false).not()) }
                    onEventChange(AuthUiEvent.RequestOtp)
                    //_authUiEffect.emit(AuthUiEffect.ShowToast("OTP sent success!"))
                }.onFailure { error ->
                _authUiState.update { it.copy(isLoading = false) }
                _authUiEffect.emit(AuthUiEffect.ShowToast(error.message.orEmpty()))
            }
        }
    }

    private fun sendOtp(isResend: Boolean = false) {
        val currentState = _authUiState.value
        viewModelScope.launch {
            if (currentState.isValidMobile.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Invalid phone number"))
                return@launch
            }

            _authUiState.update { it.copy(isLoading = true) }

            authUseCases.sendOtpUseCase.execute(currentState.mobile).onSuccess {
                _authUiState.update { it.copy(isLoading = false) }
                if (isResend.not()) _authUiEffect.emit(AuthUiEffect.NavigateToScreen(AppRoute.Otp))
                //_authUiEffect.emit(AuthUiEffect.ShowToast("OTP sent success!"))
            }.onFailure { error ->
                _authUiState.update { it.copy(isLoading = false) }
                _authUiEffect.emit(AuthUiEffect.ShowToast(error.message.orEmpty()))
            }
        }
    }

    private fun verifyOtp() {
        val currentState = _authUiState.value
        viewModelScope.launch {
            if (currentState.isValidOTP.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Invalid code"))
                return@launch
            }

            _authUiState.update { it.copy(isLoading = true) }

            authUseCases.verifyOtpUseCase.execute(currentState.otp).onSuccess {
                _authUiState.update { it.copy(isLoading = false) }
                if (currentState.isNewUser.not()){
                    isLoggedIn = true
                    _authUiEffect.emit(AuthUiEffect.NavigateToScreen(AppRoute.Main))
                }else{
                    _authUiEffect.emit(AuthUiEffect.NavigateToScreen(AppRoute.Register))
                }

                //_authUiEffect.emit(AuthUiEffect.ShowToast("OTP sent success!"))
            }.onFailure { error ->
                _authUiState.update { it.copy(isLoading = false) }
                _authUiEffect.emit(AuthUiEffect.ShowToast(error.message.orEmpty()))
            }
        }
    }

    private fun registerUser() {
        val currentState = _authUiState.value
        viewModelScope.launch {

            if (currentState.isValidName.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Provide your full name"))
                return@launch
            }

            if (currentState.isValidMobile.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Invalid phone number"))
                return@launch
            }

            if (currentState.isValidEmail.not()) {
                _authUiEffect.emit(AuthUiEffect.ShowToast("Invalid email address"))
                return@launch
            }

            _authUiState.update { it.copy(isLoading = true) }

            authUseCases.registerUserUseCase.execute(
                RegisterUserRequest(
                    currentState.name.trim(),
                    currentState.email.trim(),
                    currentState.mobile
                )
            ).onSuccess {
                _authUiState.update { it.copy(isLoading = false) }
                isLoggedIn = true
                _authUiEffect.emit(AuthUiEffect.ShowToast("Registration successful!"))
                _authUiEffect.emit(AuthUiEffect.NavigateToScreen(AppRoute.Main))
            }.onFailure { error ->
                _authUiState.update { it.copy(isLoading = false) }
                _authUiEffect.emit(AuthUiEffect.ShowToast(error.message.orEmpty()))
            }
        }
    }

}