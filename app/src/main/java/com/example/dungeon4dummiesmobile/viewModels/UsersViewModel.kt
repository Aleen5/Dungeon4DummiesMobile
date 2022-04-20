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

class UsersViewModel: ViewModel() {
    var usersComprobator by mutableStateOf(false)
    var usersModelListResponse: List<UsersModel> by mutableStateOf(listOf())
    var usersModel: UsersModel by mutableStateOf(UsersModel("idprueba", "uwusername", "uwupassword", "uwuname", "uwusurname", "uwuemail"))
    private var errorMessage: String by mutableStateOf("")
    var logged by mutableStateOf(false)
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

    fun get1User(username: String, password: String) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val user = apiServices.getUser(username)
                if (user.isSuccessful) {
                    usersModel = user.body()!!
                    if (usersModel.username == username && usersModel.password == password)
                        usersComprobator = true
                    else
                        loginFailures--
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                loginFailures--
            }
        }
    }

    fun login(username: String, password: String, onComplete: (usersModel: UsersModel?, cause: String)-> Unit) {
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
}