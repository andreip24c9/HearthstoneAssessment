package com.andrei.hearthstoneassessment.presentation.ui.list

enum class CardSetCategory(val value: String) {
    BASIC("Basic"),
    CLASSIC("Classic"),
    PROMO("Promo"),
    HALL_OF_FAME("Hall of fame"),
    NAXXRAMAS("Naxxramas")
}

fun getAllCardCategories(): List<CardSetCategory> {
    return CardSetCategory.values().asList()
}

fun getCardCategory(value: String): CardSetCategory? {
    return CardSetCategory.values().firstOrNull { it.value.equals(value, ignoreCase = true) }
}