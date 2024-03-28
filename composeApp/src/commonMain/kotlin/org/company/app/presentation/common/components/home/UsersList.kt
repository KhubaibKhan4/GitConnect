package org.company.app.presentation.common.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.company.app.domain.model.UsersItem
import org.company.app.presentation.common.components.detail.UsersDetail

@Composable
fun UserList(users: List<UsersItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp)
    ) {
        Text(
            text = "GitHub Users",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(users) { user ->
                UsersItem(usersItem = user)
            }
        }
    }
}

@Composable
fun UsersItem(
    usersItem: UsersItem,
) {
    val navigator = LocalNavigator.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navigator?.push(UsersDetail(usersItem))
            }
    ) {
        val image: Resource<Painter> = asyncPainterResource(data = usersItem.avatarUrl)
        KamelImage(
            resource = image,
            contentDescription = null,
            modifier = Modifier.size(56.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = usersItem.login,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "ID: ${usersItem.id}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}