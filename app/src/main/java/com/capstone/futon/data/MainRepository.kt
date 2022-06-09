package com.capstone.futon.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.futon.data.model.LoginResponse
import com.capstone.futon.data.remote.ApiService
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: ApiService,
    private val preference: SessionPreference) {

    private val _userLogin = MutableLiveData<String>()
    val userLogin: LiveData<String> = _userLogin

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    suspend fun registerUser(name: String, email: String, password: String): Response<String> {
        return api.register(name, email, password)
    }

    suspend fun loginUser(loginResponse: LoginResponse): Response<LoginResponse> {
        return api.login(loginResponse)
    }

    fun login2(email: String, password: String) {

        try {
            val jsonObject = JSONObject()
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            api.loginTest(jsonObject.toString())
                .enqueue(object : Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        if (response.isSuccessful) {
                            _userLogin.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("TAG", "Retrofit Fail")
                    }

                })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun register(name: String, email: String, password: String) {

        try {
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            api.register(jsonObject.toString())
                .enqueue(object : Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        if (response.isSuccessful) {
                            _toast.postValue(response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("TAG", "Retrofit Fail")
                    }

                })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}