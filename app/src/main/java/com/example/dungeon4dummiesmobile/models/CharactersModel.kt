package com.example.dungeon4dummiesmobile.models

import StatsModel

data class CharactersModel(
    val _id: String,
    val id: String,
    var name: String,
    var surname: String,
    var alias: String,
    var status: String,
    var race: String,
    val campaigns: String,
    val alignment: String,
    val level: Int,
    val exp: Int,
    var character_class: String,
    var archetype: String,
    val stats: StatsModel,
    var max_hp: Int,
    var current_hp: Int,
    val temporal_hp: Int,
    val max_mana: Int,
    val current_mana: Int,
    val adventure_journal: MutableList<String>,
    val attacks_sorceries: MutableList<String>,
    val features_traits: MutableList<String>,
    val death_saves: Int,
    val inventory: MutableList<String>,
    val backstory: String,
    val ideals: String,
    val proficiencies: String,
    val avatar: String,
    val flaws: String,
    val personality_traits: String,
    val bonds: String,
    val languages: String,
    val age: Int,
    var owner: String
)