package com.example.dungeon4dummiesmobile.viewModels

import StatsModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dungeon4dummiesmobile.models.CharactersModel
import com.example.dungeon4dummiesmobile.services.ApiServices
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel: ViewModel() {
    var charactersModelListResponse: MutableList<CharactersModel> by mutableStateOf(mutableListOf())
    var charactersModel: CharactersModel by mutableStateOf(CharactersModel("", "", "", "",
        "", "Alive", "", "","", 1, 0, "", "",
        StatsModel(0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        0, 0, 0, 0, 0, mutableListOf(""), mutableListOf(""),
        mutableListOf("", "", ""), 3, mutableListOf("", ""), "", "",
        "", "", "", "", "", "", 20, ""))

    var charactersSpoilerModel: CharactersModel by mutableStateOf(CharactersModel("", "", "Spoiler", "Spoiler",
        "Spoiler", "???", "Race is a spoiler? idk", "","Spoiler alignment", 1, 0, "The class is a spoiler", "Spoiler archetype",
        StatsModel(0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        0, 0, 0, 0, 0, mutableListOf("Spoiler"), mutableListOf("Spoiler"),
        mutableListOf("Spoiler!", "Spoiler!", "Spoiler!"), 3, mutableListOf("Spoiler", "Spoiler"), "Spoily backstory", "",
        "Proficiency in spoiler!", "", "Spoiler", "Spoilers ahead!", "Bondy spoiler", "", 20, ""))

    var statsModel: StatsModel by mutableStateOf(StatsModel(0, 0, 0, 0, 0, 0, 0, 0, 0, 20,
        0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    private var errorMessage: String by mutableStateOf("")

    fun getCharactersList() {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val charactersList = apiServices.getCharacters()
                charactersModelListResponse = charactersList as MutableList<CharactersModel>
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun genRandomCharacter(id: String) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                apiServices.genRandomCharacter(id)
            } catch (e: Exception) {
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
                    charactersModel = character.body()!!
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
                    charactersModelListResponse = cList as MutableList<CharactersModel>
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

    fun deleteCharacter(characterID: String) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                apiServices.deleteCharacter(characterID)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("CharacterDelete", errorMessage)
            }
        }
    }
}