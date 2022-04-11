package com.example.dungeon4dummiesmobile.services

import com.example.dungeon4dummiesmobile.models.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiServices {

    @GET("users")
    suspend fun getUsers() : Response<List<UsersModel>>

    @GET("users/{usename}")
    suspend fun getUser(@Path("username")username: String) : Response<UsersModel>

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
                    //tenéis que conectar el móvil a la misma red que el ordenador en el que abráis el servidor y poner su IP
                    //+ puerto en la "baseUrl"
                    .baseUrl("http://172.20.10.5:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }
            return apiServices!!
        }
    }
}