package com.example.dungeon4dummiesmobile.services

import com.example.dungeon4dummiesmobile.models.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiServices {

    @GET("users")
    suspend fun getUsers() : List<UsersModel>

    @GET("users/{username}")
    suspend fun getUser(@Path("username")username: String) : Response<UsersModel>

    @GET("users/{username}/{password}")
    suspend fun getLogin(@Path("username")username: String, @Path("password") password: String) : Response<UsersModel>

    @PUT("users/{username}")
    suspend fun updateUser(
        @Path("username")username: String,
        @Body user : UsersModel
    ) : Response<UsersModel>

    @POST("users")
    suspend fun postUser(
        @Body extra : UsersModel
    ) : Response<UsersModel>


    companion object {
        private var apiServices:ApiServices? = null

        fun getInstance() : ApiServices {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("http://192.168.1.76:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }
            return apiServices!!
        }
    }
}