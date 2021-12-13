package com.andrei.hearthstoneassessment.repository

import com.andrei.hearthstoneassessment.network.ApiService
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardDtoMapper
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard

class HearthstoneCardsRepositoryImpl(
    private val apiService: ApiService,
    private val dtoMapper: HearthstoneCardDtoMapper
) : HearthstoneCardsRepository {

    override suspend fun fetchCards(cursor: String, limit: Int): List<HearthstoneCard> {
        return dtoMapper.toDomainList(apiService.getCards(cursor, limit).data)
    }
}