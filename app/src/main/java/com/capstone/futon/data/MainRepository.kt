package com.capstone.futon.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.futon.data.model.LoginResponse
import com.capstone.futon.data.model.Plant
import com.capstone.futon.data.remote.ApiService
import kotlinx.coroutines.flow.first
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

    private val _plant = MutableLiveData<List<Plant>>()
    val plant: LiveData<List<Plant>> = _plant

    private val _animal = MutableLiveData<List<Plant>>()
    val animal: LiveData<List<Plant>> = _animal

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
                        } else {
                            _toast.postValue("Email atau password salah!")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("TAG", "Retrofit Fail")
                        _toast.postValue(t.message)
                    }

                })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    suspend fun plantList() {
        val token: String = preference.getSession().first().token
        api.plantList(token)
            .enqueue(object : Callback<List<Plant>> {
                override fun onResponse(call: Call<List<Plant>>, response: Response<List<Plant>>) {
                    if (response.isSuccessful) {
                        _animal.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Plant>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    suspend fun animalList() {
        val token: String = preference.getSession().first().token
        api.animalList(token)
            .enqueue(object : Callback<List<Plant>> {
                override fun onResponse(call: Call<List<Plant>>, response: Response<List<Plant>>) {
                    if (response.isSuccessful) {
                        _plant.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Plant>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
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