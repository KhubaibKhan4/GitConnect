package org.company.app.domain.repository

import org.company.app.data.remote.api.GithubApiClient
import org.company.app.data.repository.GithubApi
import org.company.app.domain.model.UsersItem

class Repository: GithubApi {
    override suspend fun getAllUsers(): List<UsersItem> {
        return GithubApiClient.getAllUsers()
    }

    override suspend fun getFollowers(username: String): List<UsersItem> {
        return GithubApiClient.getFollowers(username)
    }
}