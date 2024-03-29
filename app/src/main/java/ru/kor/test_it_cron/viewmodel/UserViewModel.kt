package ru.kor.test_it_cron.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.kor.test_it_cron.dto.UserInfo
import ru.kor.test_it_cron.repository.Repository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val userData = repository.userData
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.Default)

    private val _detailUserData = MutableLiveData<UserInfo?>()
    val detailUserData: LiveData<UserInfo?> = _detailUserData

    fun getUser(userLogin: String) {
        viewModelScope.launch {
            _detailUserData.value = repository.getUser(userLogin)
        }
    }

    fun resetDetailUserData() {
        _detailUserData.value = null
    }

}