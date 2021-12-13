package com.andrei.hearthstoneassessment.network

import com.andrei.hearthstoneassessment.network.model.HearthstoneCardDTO
import com.andrei.hearthstoneassessment.network.response.GenericPaginationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cards")
    suspend fun getCards(@Query("cursor") cursor: String?, @Query("limit") limit: Int) : GenericPaginationResponse<HearthstoneCardDTO>
}