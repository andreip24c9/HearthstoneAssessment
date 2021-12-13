package com.andrei.hearthstoneassessment.repository

import android.util.Log
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassessment.domain.model.PaginatedListModel
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardDTO
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardPaginatedListMapper
import com.andrei.hearthstoneassessment.network.response.GenericPaginationResponse
import com.andrei.hearthstoneassessment.presentation.AbstractApplication
import com.andrei.hearthstoneassessment.presentation.MyApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.FileNotFoundException
import java.nio.charset.Charset

@Suppress("BlockingMethodInNonBlockingContext")
class MockHearthstoneCardsRepositoryImpl(
    private val gson: Gson,
    private val application: AbstractApplication,
    private val genericListMapper: HearthstoneCardPaginatedListMapper,
) : MockHearthstoneCardsRepository {

    companion object {
        private const val TAG = "MockHSCardsRepository"
    }

    override suspend fun fetchCards(
        category: String,
        cursor: String?,
        limit: Int
    ): PaginatedListModel<HearthstoneCard> {
        val fileName = "${category.lowercase()}_mock_response_${
            when {
                cursor == "true" -> "page1.json"
                cursor != null -> "page2.json"
                else -> ""
            }
        }"
        Log.d(TAG, "fetchCards from file: $fileName")

        return try {
            val json = getJsonFromFile(fileName)
            val type = object : TypeToken<GenericPaginationResponse<HearthstoneCardDTO>>() {}.type
            val response: GenericPaginationResponse<HearthstoneCardDTO> = gson.fromJson(json, type)
            genericListMapper.mapToDomainModel(response)
        } catch (e: FileNotFoundException) {
            val responseBody: ResponseBody =
                "{}".toResponseBody("application/json".toMediaType())
            val responseError: Response<String> = Response.error(404, responseBody)
            throw HttpException(responseError)
        }
    }

    @Throws(FileNotFoundException::class)
    private fun getJsonFromFile(fileName: String): String {
        val inputStream = try {
            application.resources.assets.open(fileName)
        } catch (e: FileNotFoundException) {
            throw FileNotFoundException()
        }

        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.defaultCharset())
    }
}