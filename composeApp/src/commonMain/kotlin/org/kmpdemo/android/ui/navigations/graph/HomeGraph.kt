package org.kmpdemo.android.ui.navigations.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.kmpdemo.android.ui.view.MainScreen
import com.kmpdemo.presentation.utils.AppRoute

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(route = AppRoute.Main.route) {
        MainScreen(navController)
    }
}