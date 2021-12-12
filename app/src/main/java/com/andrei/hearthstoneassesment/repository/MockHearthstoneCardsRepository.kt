package com.andrei.hearthstoneassesment.repository

import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassesment.domain.model.PaginatedListModel

interface MockHearthstoneCardsRepository {
    suspend fun fetchCards(category:String, cursor: String?, limit: Int) : PaginatedListModel<HearthstoneCard>
}