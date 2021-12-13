package com.andrei.hearthstoneassessment.network.model

import com.andrei.hearthstoneassessment.domain.model.Mechanics
import com.andrei.hearthstoneassessment.domain.util.EntityMapper

class MechanicsDtoMapper : EntityMapper<MechanicsDTO, Mechanics> {
    override fun mapToDomainModel(dto: MechanicsDTO): Mechanics =
        Mechanics(name = dto.name)

    override fun mapFromDomainModel(domainModel: Mechanics): MechanicsDTO =
        MechanicsDTO(name = domainModel.name)
}