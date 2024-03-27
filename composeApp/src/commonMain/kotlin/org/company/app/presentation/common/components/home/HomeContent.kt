package org.company.app.presentation.common.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.company.app.domain.model.UsersItem
import org.company.app.domain.usecase.ResultState
import org.company.app.presentation.common.components.error.ErrorBox
import org.company.app.presentation.common.components.loading.LoadingBox
import org.company.app.presentation.viewmodel.MainViewModel
import org.company.app.theme.LocalThemeIsDark
import org.koin.compose.koinInject

@Composable
fun HomeContent(
    viewModel: MainViewModel = koinInject<MainViewModel>(),
) {
    var isDark by LocalThemeIsDark.current
    var usersData by remember { mutableStateOf<List<UsersItem>?>(null) }
    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }
    val state by viewModel.allUsers.collectAsState()
    when (state) {
        is ResultState.ERROR -> {
            val error = (state as ResultState.ERROR).error
            ErrorBox(error)
        }

        is ResultState.LOADING -> {
            LoadingBox()
        }

        is ResultState.SUCCESS -> {
            val response = (state as ResultState.SUCCESS).response
            usersData = response
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            usersData?.let { UserList(it) }
        }
    }
}