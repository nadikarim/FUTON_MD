package com.capstone.futon.data.remote

import com.capstone.futon.data.model.LoginResponse
import com.capstone.futon.data.model.Plant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<String>
    //Response<UserId>

    //@Headers("Accept: application/json")
    @Headers("Content-Type: application/json")
    @POST("/api/user/login")
    suspend fun login(
        @Body loginResponse: LoginResponse
    ): Response<LoginResponse>
    //Response<Authoken>

    @Headers("Content-Type: application/json")
    @POST("/api/user/login")
    fun loginTest(
        @Body response: String
    ): Call<String>

    @Headers("Content-Type: application/json")
    @POST("/api/user/register")
    fun register(
        @Body response: String
    ): Call<String>


    @GET("/api/plant/lists")
    fun plantList(
        @Header("auth-token")token: String
    ): Call<List<Plant>>

    @GET("/api/animal/lists")
    fun animalList(
        @Header("auth-token")token: String
    ): Call<List<Plant>>


    /*
    @GET("/posts")
    fun getPost(
        @Header("auth-token") token: String,
        @Query("userid")userId: String,
    ): Response<UserId, Token>
    //Response<UserId, Token>

     */
}