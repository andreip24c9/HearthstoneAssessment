package com.andrei.hearthstoneassesment.domain.util

import com.andrei.hearthstoneassesment.domain.model.PaginatedListModel
import com.andrei.hearthstoneassesment.network.response.GenericPaginationResponse

interface GenericPaginationListMapper<DTO, DomainModel>
    : EntityMapper<GenericPaginationResponse<DTO>, PaginatedListModel<DomainModel>>