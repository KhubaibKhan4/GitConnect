package org.company.app.presentation.navigation.tabs.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.company.app.presentation.navigation.screens.settings.SettingsScreen

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