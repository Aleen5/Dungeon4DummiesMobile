package com.example.dungeon4dummiesmobile.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dungeon4dummiesmobile.models.UsersModel
import com.example.dungeon4dummiesmobile.services.ApiServices
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel: ViewModel() {
    var usersModelListResponse: List<UsersModel> by mutableStateOf(listOf())
    var usersModel: UsersModel by mutableStateOf(UsersModel("idprueba", "uwusername", "uwupassword", "uwuname", "uwusurname", "uwuemail", listOf("uwu", "uwu")))
    private var errorMessage: String by mutableStateOf("")
    var loginFailures by mutableStateOf(3)

    fun getUsersList() {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val usersList = apiServices.getUsers()
                usersModelListResponse = usersList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1User(username: String, onComplete: (usersModel: UsersModel?) -> Unit) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val user = apiServices.getUser(username)
                if (user.isSuccessful) {
                    onComplete(user.body()!!)
                } else {
                    onComplete(null)
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                onComplete(null)
                loginFailures--
            }
        }
    }

    fun login(username: String, password: String, onComplete: (usersModel: UsersModel?, cause: String) -> Unit) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val user = apiServices.getLogin(username, password)
                if (user.isSuccessful) {
                    onComplete(user.body()!!, "good")

                } else {
                    onComplete(null, "bad")
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                onComplete(null, "catch")
                loginFailures--
            }
        }
    }

    fun postUser(user: UsersModel) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val userP = apiServices.postUser(user)
                if (userP.isSuccessful) {
                    usersModel = userP.body()!!
                } else {
                    Log.d("Ticket", "Ticket error")
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TICKET", errorMessage)
            }
        }
    }
}