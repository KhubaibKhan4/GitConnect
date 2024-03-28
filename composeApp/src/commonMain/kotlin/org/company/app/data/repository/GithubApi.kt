package org.company.app.data.repository

import org.company.app.domain.model.UsersItem
import org.company.app.domain.model.details.UserDetail

interface GithubApi {
    suspend fun getAllUsers(): List<UsersItem>
    suspend fun getFollowers(username: String): UserDetail
}