package com.capstone.futon.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

class SessionPreference @Inject constructor(@ApplicationContext val context: Context){

    private val dataStore = context.dataStore

    fun getSession(): Flow<Session> {
        return dataStore.data.map { preferences->
            Session(
                preferences[TOKEN]?: "",
                preferences[LOGIN_STATE]?: true
            )
        }
    }

    suspend fun setSession(session: Session) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = session.token
            preferences[LOGIN_STATE] = session.sessionStatus
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
            preferences[LOGIN_STATE] = true
        }
    }

    companion object {

        private val TOKEN = stringPreferencesKey("token")
        private val LOGIN_STATE = booleanPreferencesKey("state")

    }

}