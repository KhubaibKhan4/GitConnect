package org.company.app.presentation.navigation.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.company.app.presentation.common.components.home.HomeContent

class HomeScreen: Screen{
    @Composable
    override fun Content() {
        HomeContent()
    }
}