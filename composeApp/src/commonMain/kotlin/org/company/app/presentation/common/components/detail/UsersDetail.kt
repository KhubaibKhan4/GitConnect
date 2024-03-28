package org.company.app.presentation.common.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.company.app.domain.model.UsersItem
import org.company.app.domain.model.details.UserDetail
import org.company.app.domain.usecase.ResultState
import org.company.app.presentation.common.components.error.ErrorBox
import org.company.app.presentation.common.components.loading.LoadingBox
import org.company.app.presentation.viewmodel.MainViewModel
import org.koin.compose.koinInject

class UsersDetail(
    private val usersItem: UsersItem,
) : Screen {
    @Composable
    override fun Content() {
        UsersDetailContent(usersItem)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersDetailContent(
    usersItem: UsersItem,
    viewModel: MainViewModel = koinInject<MainViewModel>(),
) {
    var usersData by remember { mutableStateOf<UserDetail?>(null) }
    LaunchedEffect(Unit) {
        viewModel.getUsersDetail("KhubaibKhan4")
    }
    val state by viewModel.getUsersDetails.collectAsState()
    when (state) {
        is ResultState.ERROR -> {
            val error = (state as ResultState.ERROR).error
            ErrorBox(error)
        }

        ResultState.LOADING -> {
            LoadingBox()
        }

        is ResultState.SUCCESS -> {
            val response = (state as ResultState.SUCCESS).response
            usersData = response
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(usersItem.login)
                },
                actions = {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share"
                    )
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image: Resource<Painter> = asyncPainterResource(data = usersItem.avatarUrl)
                KamelImage(
                    resource = image,
                    contentDescription = "Avatar",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = usersData?.name.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    )
                    Text(
                        text = usersItem.login,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    )
                }
            }

        }
    }
}