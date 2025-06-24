package org.kmpdemo.android.ui.navigations.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kmpdemo.presentation.utils.AppRoute

fun NavGraphBuilder.locationGraph(navController: NavHostController) {
    composable(AppRoute.Location.route) {
//        LocationScreen(
//            onLocationFetched = {
//                navController.navigate(AppRoute.Main.route) {
//                    popUpTo(AppRoute.Location.route) { inclusive = true }
//                }
//            }
//        )
    }
}