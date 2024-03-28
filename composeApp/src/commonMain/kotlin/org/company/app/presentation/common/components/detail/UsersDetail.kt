package org.company.app.presentation.common.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.company.app.domain.model.UsersItem
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            Text(
                text = "User Details",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            val image: Resource<Painter> = asyncPainterResource(data = usersItem.avatarUrl)
            KamelImage(
                resource = image,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
                    .clip(CircleShape),
                onLoading = {
                    CircularProgressIndicator(
                        progress = {
                            it
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Login: ${usersItem.login}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "ID: ${usersItem.id}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Profile URL: ${usersItem.htmlUrl}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}