package com.andrei.hearthstoneassessment.repository

import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard

interface HearthstoneCardsRepository {
    suspend fun fetchCards(cursor: String, limit: Int) : List<HearthstoneCard>
}