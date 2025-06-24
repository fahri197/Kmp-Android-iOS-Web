package org.kmpdemo.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.kmpdemo.presentation.di.rememberKoinInstance
import com.kmpdemo.presentation.ui.theme.AppTheme
import com.kmpdemo.presentation.utils.AppRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmpdemo.android.ui.navigations.graph.MainGraph
import org.kmpdemo.android.ui.viewmodel.MainViewModel

@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val mainViewModel: MainViewModel = rememberKoinInstance()

        val startDestination = if (mainViewModel.isLoggedIn) {
            AppRoute.Main.route
        } else {
            AppRoute.Login.route
        }

        MainGraph(navController = navController, startDestination = startDestination)
    }
}