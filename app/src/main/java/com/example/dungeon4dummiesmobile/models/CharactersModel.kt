package com.example.dungeon4dummiesmobile.models

import StatsModel

data class CharactersModel(
    val _id: String,
    val id: String,
    val name: String,
    val surname: String,
    val alias: String,
    val status: String,
    val race: String,
    val alignment: String,
    val level: Int,
    val exp: Int,
    val character_class: String,
    val archetype: String,
    val stats: StatsModel,
    val max_hp: Int,
    val current_hp: Int,
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
    val avatar: String
)