package org.company.app.presentation.common.components.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    usersData?.let { UserList(it) }
}