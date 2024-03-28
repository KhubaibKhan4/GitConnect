package org.company.app.data.repository

import org.company.app.domain.model.UsersItem

interface GithubApi {
    suspend fun getAllUsers(): List<UsersItem>
    suspend fun getFollowers(username: String): List<UsersItem>
}