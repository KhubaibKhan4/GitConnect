package org.company.app.presentation.navigation.tabs.notifications

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.company.app.presentation.navigation.screens.notifications.NotificationsScreen

object NotificationsTab : Tab {
    @Composable
    override fun Content() {
        Navigator(NotificationsScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Notifications)
            val title = "Notifications"
            val index: UShort = 1u
            return TabOptions(index, title, icon)
        }
}