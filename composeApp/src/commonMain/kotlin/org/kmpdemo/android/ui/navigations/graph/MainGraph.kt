package org.kmpdemo.android.ui.navigations.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeGraph(navController)
        authGraph(navController)
        locationGraph(navController)
    }
}