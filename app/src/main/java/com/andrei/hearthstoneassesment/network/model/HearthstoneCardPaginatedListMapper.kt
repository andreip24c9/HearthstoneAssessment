package com.andrei.hearthstoneassesment.network.model

import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassesment.domain.model.PaginatedListModel
import com.andrei.hearthstoneassesment.domain.util.GenericPaginationListMapper
import com.andrei.hearthstoneassesment.network.response.GenericPaginationResponse

class HearthstoneCardPaginatedListMapper :
    GenericPaginationListMapper<HearthstoneCardDTO, HearthstoneCard> {

    override fun mapToDomainModel(dto: GenericPaginationResponse<HearthstoneCardDTO>) =
        PaginatedListModel(
            dto.data.map { HearthstoneCardDtoMapper().mapToDomainModel(it) },
            MetaDtoMapper().mapToDomainModel(dto.meta)
        )
}