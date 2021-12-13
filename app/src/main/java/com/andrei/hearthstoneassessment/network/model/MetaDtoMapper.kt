package com.andrei.hearthstoneassessment.network.model

import com.andrei.hearthstoneassessment.domain.model.MetaModel
import com.andrei.hearthstoneassessment.domain.model.PaginationModel
import com.andrei.hearthstoneassessment.domain.util.EntityMapper

class MetaDtoMapper : EntityMapper<MetaDTO, MetaModel> {
    override fun mapToDomainModel(dto: MetaDTO): MetaModel =
        MetaModel(dto.count, PaginationDtoMapper().mapToDomainModel(dto.pagination))
}

class PaginationDtoMapper : EntityMapper<PaginationDTO, PaginationModel> {
    override fun mapToDomainModel(dto: PaginationDTO): PaginationModel =
        PaginationModel(dto.cursor, dto.limit)
}