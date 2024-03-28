package org.company.app.presentation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.company.app.domain.model.UsersItem
import org.company.app.domain.model.details.UserDetail
import org.company.app.domain.repository.Repository
import org.company.app.domain.usecase.ResultState

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _allUsers = MutableStateFlow<ResultState<List<UsersItem>>>(ResultState.LOADING)
    var allUsers: StateFlow<ResultState<List<UsersItem>>> = _allUsers.asStateFlow()

    private val _getUsersDetails = MutableStateFlow<ResultState<UserDetail>>(ResultState.LOADING)
    var getUsersDetails: StateFlow<ResultState<UserDetail>> = _getUsersDetails.asStateFlow()
    fun getAllUsers() {
        viewModelScope.launch {
            _allUsers.value = ResultState.LOADING
            try {
                val response = repository.getAllUsers()
                _allUsers.value = ResultState.SUCCESS(response)
            } catch (e: Exception) {
                _allUsers.value = ResultState.ERROR(e)
            }
        }
    }

    fun getUsersDetail(username: String) {
        viewModelScope.launch {
            _getUsersDetails.value = ResultState.LOADING
            try {
                val response = repository.getUsersDetails(username)
                _getUsersDetails.value = ResultState.SUCCESS(response)
            } catch (e: Exception) {
                _getUsersDetails.value = ResultState.ERROR(e)
            }
        }
    }
}