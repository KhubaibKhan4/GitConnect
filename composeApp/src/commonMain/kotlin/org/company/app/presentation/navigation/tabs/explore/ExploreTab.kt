package org.company.app.presentation.navigation.tabs.explore

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.company.app.presentation.navigation.screens.explore.ExploreScreen

object ExploreTab : Tab {
    @Composable
    override fun Content() {
        Navigator(ExploreScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Search)
            val title = "Explore"
            val index: UShort = 2u
            return TabOptions(index, title, icon)
        }
}