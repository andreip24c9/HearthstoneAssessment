package com.andrei.hearthstoneassesment.domain.model

data class PaginatedListModel<T>(
    val data: List<T>,
    val meta: MetaModel
) {
    fun hasMoreItems(): Boolean {
        return meta.count == meta.pagination.limit && meta.pagination.cursor != null
    }
}

data class MetaModel(
    val count: Int,
    val pagination: PaginationModel
)

data class PaginationModel(
    val cursor: String?,
    val limit: Int
)