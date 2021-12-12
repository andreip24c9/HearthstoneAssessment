package com.andrei.hearthstoneassesment.network

import com.andrei.hearthstoneassesment.network.model.HearthstoneCardDTO
import com.andrei.hearthstoneassesment.network.response.GenericPaginationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cards")
    suspend fun getCards(@Query("cursor") cursor: String?, @Query("limit") limit: Int) : GenericPaginationResponse<HearthstoneCardDTO>
}