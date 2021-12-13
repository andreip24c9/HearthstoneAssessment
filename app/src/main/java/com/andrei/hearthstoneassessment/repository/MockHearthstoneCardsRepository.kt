package com.andrei.hearthstoneassessment.repository

import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.domain.model.PaginatedListModel

interface MockHearthstoneCardsRepository {
    suspend fun fetchCards(category:String, cursor: String?, limit: Int) : PaginatedListModel<HearthstoneCard>
}