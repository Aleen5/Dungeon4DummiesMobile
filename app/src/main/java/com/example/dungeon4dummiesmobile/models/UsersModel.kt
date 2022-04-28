package com.example.dungeon4dummiesmobile.models

data class UsersModel(
    val _id: String,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val email: String,
    val characters: MutableList<String>
) {
    companion object {
        val usersArray: MutableList<UsersModel> = mutableListOf(
            UsersModel(
                "1",
                "Sainorum",
                "1234",
                "DJ",
                "Saineres",
                "sainero@gmail.dev",
                mutableListOf("1", "2")
            ),
            UsersModel(
                "2",
                "DJCzaplicki",
                "1234",
                "DJ",
                "Czaplicki",
                "dj@gmail.dev",
                mutableListOf("1", "2")
            ),
            UsersModel(
                "3",
                "AlexD",
                "1234",
                "Alex",
                "Sepiro",
                "arekusu@gmail.dev",
                mutableListOf("1", "2")
            )
        )
    }
}
