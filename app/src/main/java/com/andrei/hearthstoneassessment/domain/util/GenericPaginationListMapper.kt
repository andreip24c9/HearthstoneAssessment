package com.andrei.hearthstoneassessment.domain.util

import com.andrei.hearthstoneassessment.domain.model.PaginatedListModel
import com.andrei.hearthstoneassessment.network.response.GenericPaginationResponse

interface GenericPaginationListMapper<DTO, DomainModel>
    : EntityMapper<GenericPaginationResponse<DTO>, PaginatedListModel<DomainModel>>