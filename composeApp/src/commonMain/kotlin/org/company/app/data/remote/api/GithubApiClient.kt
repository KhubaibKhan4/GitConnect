package org.company.app.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.company.app.domain.model.UsersItem
import org.company.app.domain.model.details.UserDetail
import org.company.app.utils.Constant.BASE_URL
import org.company.app.utils.Constant.TIME_OUT

object GithubApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }
        install(HttpTimeout) {
            socketTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
            requestTimeoutMillis = TIME_OUT
        }
    }

    suspend fun getAllUsers(): List<UsersItem> {
        return client.get("$BASE_URL/users").body()
    }

    suspend fun getFollowers(username: String): UserDetail {
        return client.get("$BASE_URL/users/$username/followers").body()
    }
}