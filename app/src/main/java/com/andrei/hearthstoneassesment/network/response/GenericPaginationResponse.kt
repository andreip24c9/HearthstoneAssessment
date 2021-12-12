package com.andrei.hearthstoneassesment.network.response

import com.andrei.hearthstoneassesment.network.model.MetaDTO
import com.google.gson.annotations.SerializedName

class GenericPaginationResponse<T>(
    @SerializedName("data") val data: List<T>,
    @SerializedName("meta") val meta: MetaDTO
)