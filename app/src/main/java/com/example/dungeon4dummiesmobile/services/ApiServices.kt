package com.example.dungeon4dummiesmobile.services

import com.example.dungeon4dummiesmobile.models.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiServices {

    // USERS

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

    // CHARACTERS

    @GET("characters")
    suspend fun getCharacters() : List<CharactersModel>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id")id: String) : Response<CharactersModel>

    @PUT("characters/{id}")
    suspend fun updateCharacter(
        @Path("id")id: String,
        @Body character : CharactersModel
    ) : Response<CharactersModel>

    @POST("characters")
    suspend fun postCharacter(
        @Body extra : CharactersModel
    ) : Response<CharactersModel>


    companion object {
        private var apiServices:ApiServices? = null

        fun getInstance() : ApiServices {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("http://172.26.8.72:8080/")                  // IP del curro
                    //.baseUrl("http://192.168.1.76:8080/")            // IP de casa
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }
            return apiServices!!
        }
    }
}