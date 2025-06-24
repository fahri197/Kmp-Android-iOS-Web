package org.kmpdemo.android.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.kmpdemo.android.ui.navigations.BottomNavigationBar
import org.kmpdemo.android.ui.navigations.NavScreen
import com.kmpdemo.diy.ui.view.DIYScreen
import com.kmpdemo.home.ui.view.HomeScreen
import com.kmpdemo.profile.ui.view.ProfileScreen
import com.kmpdemo.schedule.ui.view.ScheduleScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val bottomNavController = rememberNavController()
    val currentBackStackEntry by bottomNavController.currentBackStackEntryAsState()

    // Determine title dynamically based on current destination
    val title = NavScreen.fromRoute(currentBackStackEntry?.destination?.route)?.title ?: "Home"

    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = title) }
//            )
//        },
        bottomBar = {
            BottomNavigationBar(
                navController = bottomNavController,
                currentRoute = currentBackStackEntry?.destination?.route
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = NavScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavScreen.Home.route) { HomeScreen() }
            composable(NavScreen.DIY.route) { DIYScreen() }
            composable(NavScreen.Schedule.route) { ScheduleScreen() }
            composable(NavScreen.Profile.route) { ProfileScreen() }
        }
    }
}