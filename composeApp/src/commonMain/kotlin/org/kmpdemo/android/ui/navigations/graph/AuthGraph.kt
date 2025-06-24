package org.kmpdemo.android.ui.navigations.graph

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmpdemo.presentation.utils.AppRoute
import com.kmpdemo.auth.ui.view.LoginScreen
import com.kmpdemo.auth.ui.view.RegisterScreen
import com.kmpdemo.auth.ui.view.VerifyOtpScreen
import com.kmpdemo.auth.ui.viewmodel.AuthViewModel
import com.kmpdemo.presentation.di.rememberKoinInstance

fun NavGraphBuilder.authGraph(navController: NavHostController) {

    composable(AppRoute.Login.route) {
        val authViewModel: AuthViewModel = rememberKoinInstance()
        val uiState by authViewModel.authUiState.collectAsStateWithLifecycle()
        LoginScreen(
            uiState = uiState,
            onEvent = authViewModel::onEventChange,
            uiEffect = authViewModel.authUiEffect,
            onNavigateToScreen = { appRoute -> navController.navigate(appRoute.route) }
        )
    }

    composable(route = AppRoute.Otp.route) { backStackEntry ->

        val loginEntry = remember(backStackEntry) {
            navController.getBackStackEntry(AppRoute.Login.route)
        }
        val authViewModel: AuthViewModel = rememberKoinInstance()
        val uiState by authViewModel.authUiState.collectAsStateWithLifecycle()

        VerifyOtpScreen(
            uiState = uiState,
            onEvent = authViewModel::onEventChange,
            uiEffect = authViewModel.authUiEffect,
            onNavigateToScreen = { appRoute -> navController.navigate(appRoute.route) }
        )
    }

    composable(AppRoute.Register.route) { backStackEntry ->
        val loginEntry = remember(backStackEntry) {
            navController.getBackStackEntry(AppRoute.Login.route)
        }
        val authViewModel: AuthViewModel = rememberKoinInstance()
        val uiState by authViewModel.authUiState.collectAsStateWithLifecycle()

        RegisterScreen(
            uiState = uiState,
            onEvent = authViewModel::onEventChange,
            uiEffect = authViewModel.authUiEffect,
            onNavigateToScreen = { appRoute -> navController.navigate(appRoute.route) }
        )
    }
}