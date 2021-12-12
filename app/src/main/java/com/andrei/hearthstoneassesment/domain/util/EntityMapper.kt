package com.andrei.hearthstoneassesment.domain.util

interface EntityMapper<DTO, DomainModel> {
    fun mapToDomainModel(dto: DTO): DomainModel

    // this method is optional as some models never need to be mapped to DTOs
    fun mapFromDomainModel(domainModel: DomainModel): DTO {
        throw Exception("method mapFromDomainModel not implemented")
    }
}