package com.andrei.hearthstoneassessment.network.model

data class HearthstoneCardDTO(
    val cardId: String,
    val name: String,
    val cardSet: String,
    val type: String?,
    val rarity: String?,
    val cost: Int?,
    val attack: Int?,
    val health: Int?,
    val durability: Int?,
    val text: String?,
    val flavor: String?,
    val collectible: String?,
    val elite: String?,
    val playerClass: String,
    val howToGet: String?,
    val howToGetGold: String?,
    val img: String?,
    val imgGold: String?,
    val locale: String,
    val mechanics: List<MechanicsDTO>?,
)