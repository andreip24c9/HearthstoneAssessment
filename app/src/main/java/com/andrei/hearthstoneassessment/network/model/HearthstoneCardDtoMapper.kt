package com.andrei.hearthstoneassessment.network.model

import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.domain.util.EntityMapper

class HearthstoneCardDtoMapper : EntityMapper<HearthstoneCardDTO, HearthstoneCard> {
    override fun mapToDomainModel(dto: HearthstoneCardDTO): HearthstoneCard =
        HearthstoneCard(
            cardId = dto.cardId,
            name = dto.name,
            cardSet = dto.cardSet,
            type = dto.type,
            rarity = dto.rarity,
            cost = dto.cost,
            attack = dto.attack,
            health = dto.health,
            durability = dto.durability,
            text = dto.text,
            flavor = dto.flavor,
            collectible = dto.collectible,
            elite = dto.elite,
            playerClass = dto.playerClass,
            howToGet = dto.howToGet,
            howToGetGold = dto.howToGetGold,
            img = dto.img,
            imgGold = dto.imgGold,
            locale = dto.locale,
            mechanics = dto.mechanics?.map { MechanicsDtoMapper().mapToDomainModel(it) }
        )


    override fun mapFromDomainModel(domainModel: HearthstoneCard): HearthstoneCardDTO =
        HearthstoneCardDTO(
            cardId = domainModel.cardId,
            name = domainModel.name,
            cardSet = domainModel.cardSet,
            type = domainModel.type,
            rarity = domainModel.rarity,
            cost = domainModel.cost,
            attack = domainModel.attack,
            health = domainModel.health,
            durability = domainModel.durability,
            text = domainModel.text,
            flavor = domainModel.flavor,
            collectible = domainModel.collectible,
            elite = domainModel.elite,
            playerClass = domainModel.playerClass,
            howToGet = domainModel.howToGet,
            howToGetGold = domainModel.howToGetGold,
            img = domainModel.img,
            imgGold = domainModel.imgGold,
            locale = domainModel.locale,
            mechanics = domainModel.mechanics?.map { MechanicsDtoMapper().mapFromDomainModel(it) }
        )

    fun toDomainList(initial: List<HearthstoneCardDTO>) : List<HearthstoneCard> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<HearthstoneCard>) : List<HearthstoneCardDTO> {
        return initial.map { mapFromDomainModel(it) }
    }

//    fun toDomainPaginatedList(initial: GenericPaginationResponse<HearthstoneCardDTO>) : PaginatedListModel<HearthstoneCard> {
//        return
//    }
}