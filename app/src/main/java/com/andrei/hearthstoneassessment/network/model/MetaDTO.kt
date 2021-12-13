package com.andrei.hearthstoneassessment.network.model

import com.google.gson.annotations.SerializedName

class MetaDTO(
    @SerializedName("count") val count: Int,
    @SerializedName("pagination") val pagination: PaginationDTO
)

class PaginationDTO(
    @SerializedName("next_cursor") val cursor: String?,
    @SerializedName("limit") val limit: Int
)