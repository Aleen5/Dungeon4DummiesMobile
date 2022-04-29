package com.example.dungeon4dummiesmobile.viewModels

import StatsModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.services.ApiServices
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel: ViewModel() {
    var charactersModelListResponse: List<CharactersModel> by mutableStateOf(listOf())
    var charactersModel: CharactersModel by mutableStateOf(CharactersModel("uwuid", "uwuid", "uwuname", "uwusurname",
        "uwualias", "uwustatus", "uwurace", "uwualignment", 10, 4000, "uwuclass", "uwuarch",
        StatsModel(25, 20, 30, 25, 30, 15, 20, 30, 10, 20,
            10, 30, 35, 10, 20, 25, 20, 10, 10, 50, 30, 35),
        35, 20, 0, 20, 15, mutableListOf("10/30/40: XD", "11/30/40: XD2"), mutableListOf("XD: XD", "XDXD: XDXDXD"),
        mutableListOf("XD", "XDXD", "XD"), 3, mutableListOf("XD", "XD2"), "uwubackstory", "uwuideals",
        "uwuproficiencies", "uwuavatar", "a"))
    private var errorMessage: String by mutableStateOf("")

    fun getCharactersList() {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val charactersList = apiServices.getCharacters()
                charactersModelListResponse = charactersList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1Character(id: String, onComplete: (charactersModel: CharactersModel?) -> Unit) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val character = apiServices.getCharacter(id)
                if (character.isSuccessful) {
                    onComplete(character.body()!!)
                } else {
                    onComplete(null)
                }
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                onComplete(null)
            }
        }
    }

    fun getUsersCharacters(owner: String, onComplete: (charactersList: List<CharactersModel>) -> Unit) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val cList = apiServices.getUsersCharacters(owner)
                if (!cList.isNullOrEmpty()) {
                    onComplete(cList)
                    charactersModelListResponse = cList
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("Character", errorMessage)
            }
        }
    }

    fun updateCharacter(character: CharactersModel) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val characterP = apiServices.updateCharacter(character.id, character)
                if (characterP.isSuccessful) {
                    charactersModel = characterP.body()!!
                } else {
                    Log.d("Characters", "Characters put error")
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("Characters", errorMessage)
            }
        }
    }

    fun postCharacter(character: CharactersModel) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val characterP = apiServices.postCharacter(character)
                if (characterP.isSuccessful) {
                    charactersModel = characterP.body()!!
                } else {
                    Log.d("Characters", "Characters post error")
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("Characters", errorMessage)
            }
        }
    }
}