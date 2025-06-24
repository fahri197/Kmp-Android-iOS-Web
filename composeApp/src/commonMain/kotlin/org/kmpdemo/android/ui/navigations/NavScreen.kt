package org.kmpdemo.android.ui.navigations

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

sealed class NavScreen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : NavScreen("home", "Home", Icons.Default.Home)
    data object DIY : NavScreen("diy", "DIY", Icons.Default.Build)
    data object Schedule : NavScreen("schedule", "Schedule", Icons.Default.DateRange)
    data object Profile : NavScreen("profile", "Profile", Icons.Default.AccountCircle)

    companion object {
        private val items = listOf(Home, DIY, Schedule, Profile)
        fun fromRoute(route: String?): NavScreen? {
            return items.find { it.route == route }
        }
    }
}