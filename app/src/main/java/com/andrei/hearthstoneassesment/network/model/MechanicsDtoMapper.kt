package com.andrei.hearthstoneassesment.network.model

import com.andrei.hearthstoneassesment.domain.model.Mechanics
import com.andrei.hearthstoneassesment.domain.util.EntityMapper

class MechanicsDtoMapper : EntityMapper<MechanicsDTO, Mechanics> {
    override fun mapToDomainModel(dto: MechanicsDTO): Mechanics =
        Mechanics(name = dto.name)

    override fun mapFromDomainModel(domainModel: Mechanics): MechanicsDTO =
        MechanicsDTO(name = domainModel.name)
}