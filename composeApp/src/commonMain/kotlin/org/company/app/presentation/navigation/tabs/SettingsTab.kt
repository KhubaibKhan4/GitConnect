package org.company.app.presentation.navigation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.company.app.presentation.navigation.screens.ExploreScreen
import org.company.app.presentation.navigation.screens.HomeScreen
import org.company.app.presentation.navigation.screens.NotificationsScreen
import org.company.app.presentation.navigation.screens.SettingsScreen

object SettingsTab : Tab {
    @Composable
    override fun Content() {
        Navigator(SettingsScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Settings)
            val title = "Settings"
            val index: UShort = 3u
            return TabOptions(index, title, icon)
        }
}