package org.company.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.company.app.presentation.navigation.model.NavigationItem
import org.company.app.presentation.navigation.sidebar.NavigationSideBar
import org.company.app.presentation.navigation.tabs.explore.ExploreTab
import org.company.app.presentation.navigation.tabs.home.HomeTab
import org.company.app.presentation.navigation.tabs.notifications.NotificationsTab
import org.company.app.presentation.navigation.tabs.settings.SettingsTab
import org.company.app.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    AppContent()
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppContent() {
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Notification",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications
        ),
        NavigationItem(
            title = "Explore",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        ),
    )
    val windowClass = calculateWindowSizeClass()
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    TabNavigator(HomeTab) { tabNavigator ->
        Scaffold(
            bottomBar = {
                if (!showNavigationRail) {
                    NavigationBar(
                        modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.ime),
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = contentColorFor(Color.Red),
                        tonalElevation = 8.dp
                    ) {
                        TabItem(HomeTab)
                        TabItem(NotificationsTab)
                        TabItem(ExploreTab)
                        TabItem(SettingsTab)
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                    start = if (showNavigationRail) 80.dp else 6.dp
                )
            ) {
                CurrentTab()
            }
        }
    }
    if (showNavigationRail) {
        NavigationSideBar(
            items = items,
            selectedItemIndex = selectedItemIndex,
            onNavigate = {
                selectedItemIndex = it
            }
        )

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(start = 80.dp)
        ) {
            when (selectedItemIndex) {
                0 -> {

                }

                1 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        TabNavigator(NotificationsTab)
                    }
                }

                2 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        TabNavigator(ExploreTab)
                    }
                }

                3 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        TabNavigator(SettingsTab)
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.TabItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
        },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(
                    painter = painter,
                    contentDescription = null
                )
            }
        },
        label = {
            tab.options.title.let { title ->
                Text(
                    title,
                    fontSize = 12.sp
                )
            }
        },
        enabled = true,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Red,
            unselectedIconColor = Color.Black,
            selectedTextColor = Color.Red,
            unselectedTextColor = Color.Black
        )
    )
}

internal expect fun openUrl(url: String?)