package com.andrei.hearthstoneassesment.repository

import com.andrei.hearthstoneassesment.network.ApiService
import com.andrei.hearthstoneassesment.network.model.HearthstoneCardDtoMapper
import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard

class HearthstoneCardsRepositoryImpl(
    private val apiService: ApiService,
    private val dtoMapper: HearthstoneCardDtoMapper
) : HearthstoneCardsRepository {

    override suspend fun fetchCards(cursor: String, limit: Int): List<HearthstoneCard> {
        return dtoMapper.toDomainList(apiService.getCards(cursor, limit).data)
    }
}