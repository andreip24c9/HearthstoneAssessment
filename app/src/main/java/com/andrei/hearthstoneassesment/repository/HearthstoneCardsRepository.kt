package com.andrei.hearthstoneassesment.repository

import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard

interface HearthstoneCardsRepository {
    suspend fun fetchCards(cursor: String, limit: Int) : List<HearthstoneCard>
}