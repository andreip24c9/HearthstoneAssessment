package com.andrei.hearthstoneassessment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HearthstoneCard(
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
    val mechanics: List<Mechanics>?,
) : Parcelable {
    constructor(
        name: String,
        cardSet: String,
        rarity: String?,
        img: String?,
        health: Int?,
        attack: Int?,
        durability: Int?
    )
            : this(
        "",
        name,
        cardSet,
        "",
        rarity,
        0,
        attack,
        health,
        durability,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        img,
        "",
        "",
        mutableListOf()
    )
}

@Parcelize
class HearthstoneCardList(
    val cardList: List<HearthstoneCard>
) : Parcelable
