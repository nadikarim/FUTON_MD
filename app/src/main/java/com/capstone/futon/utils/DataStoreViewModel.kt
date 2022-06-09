package com.capstone.futon.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.futon.data.Session
import com.capstone.futon.data.SessionPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel@Inject constructor(private val preference: SessionPreference): ViewModel() {

    fun getSession(): LiveData<Session> {
        return preference.getSession().asLiveData()
    }

    fun setSession(session: Session) {
        viewModelScope.launch {
            preference.setSession(session)
        }
    }

    fun logout() {
        viewModelScope.launch {
            preference.logout()
        }
    }

}