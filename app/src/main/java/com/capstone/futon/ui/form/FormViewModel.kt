package com.capstone.futon.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.futon.data.MainRepository
import com.capstone.futon.data.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    //private val _token: MutableLiveData<Response<String>> = MutableLiveData()
    //val token: LiveData<Response<String>> = _token
    //var myResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    val userLogin: LiveData<String> = repository.userLogin
    val toast: LiveData<String> = repository.toast

    fun login(loginResponse: LoginResponse) {
        viewModelScope.launch {
            val response = repository.loginUser(loginResponse)
            //_token.postValue(response.raw())
        }
    }

    fun login2(email: String, password: String) {
        repository.login2(email, password)
    }

    fun register(name: String, email: String, password: String) {
        repository.register(name, email, password)
    }



}