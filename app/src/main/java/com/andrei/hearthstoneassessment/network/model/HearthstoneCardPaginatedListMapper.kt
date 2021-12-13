package com.andrei.hearthstoneassessment.network.model

import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.domain.model.PaginatedListModel
import com.andrei.hearthstoneassessment.domain.util.GenericPaginationListMapper
import com.andrei.hearthstoneassessment.network.response.GenericPaginationResponse

class HearthstoneCardPaginatedListMapper :
    GenericPaginationListMapper<HearthstoneCardDTO, HearthstoneCard> {

    override fun mapToDomainModel(dto: GenericPaginationResponse<HearthstoneCardDTO>) =
        PaginatedListModel(
            dto.data.map { HearthstoneCardDtoMapper().mapToDomainModel(it) },
            MetaDtoMapper().mapToDomainModel(dto.meta)
        )
}