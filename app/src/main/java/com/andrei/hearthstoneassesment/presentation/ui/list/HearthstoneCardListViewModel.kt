package com.andrei.hearthstoneassesment.presentation.ui.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.domain.model.HearthstoneCard
import com.andrei.hearthstoneassesment.presentation.components.ErrorMessage
import com.andrei.hearthstoneassesment.repository.HearthstoneCardsRepository
import com.andrei.hearthstoneassesment.repository.MockHearthstoneCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HearthstoneCardListViewModel
@Inject
constructor(
    private val repository: HearthstoneCardsRepository,
    private val mockRepository: MockHearthstoneCardsRepository,
) : ViewModel() {

    val cards: MutableState<List<HearthstoneCard>> = mutableStateOf(ArrayList())
    val selectedCategory: MutableState<CardSetCategory> = mutableStateOf(CardSetCategory.BASIC)
    var pageLoader = mutableStateOf(false)
    var listLoader = mutableStateOf(false)
    var page = mutableStateOf(1)
    var gridLayout = mutableStateOf(false)
    var initialLoadError: MutableState<ErrorMessage?> = mutableStateOf(null)
    var nextPageLoadError: MutableState<ErrorMessage?> = mutableStateOf(null)

    private var cursor: String? = "true"
    private var hasMorePages = true
    var listScrollPosition = 0

    var firstVisibleListIndex: Int = 0

    companion object {
        const val PAGE_SIZE = 25
    }

    init {
        Log.d("hsvm", "init")
        firstPage()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("hsvm", "onclear")
    }

    var fetchFirstPageJob: Job? = null
    var fetchNextPageJob: Job? = null
    private fun firstPage() {
        cancelAllJobs()
        initialLoadError.value = null
        fetchFirstPageJob = viewModelScope.launch {
            try {
                pageLoader.value = true
                resetPagination()
                delay(500) // todo remove this later
                cards.value = fetchCards()
                if (cards.value.isNullOrEmpty()) initialLoadError.value =
                    ErrorMessage(
                        titleRes = R.string.empty_error_title,
                        bodyRes = R.string.empty_error_body
                    )
            } catch (exception: Exception) {
                cancel()
                pageLoader.value = false
                initialLoadError.value = ErrorMessage(
                    titleRes = R.string.unown_error_title,
                    bodyRes = R.string.unown_error_body
                )
            }
        }
        fetchFirstPageJob?.invokeOnCompletion {
            pageLoader.value = false
            it?.let {
                fetchFirstPageJob?.cancel()
                initialLoadError.value = ErrorMessage(
                    titleRes = R.string.unown_error_title,
                    bodyRes = R.string.unown_error_body
                )
            }
        }
    }

    fun nextPage() {
        fetchNextPageJob = viewModelScope.launch {
            try {
                // to prevent duplicate requests
                if ((listScrollPosition + 1) >= (page.value * PAGE_SIZE) && hasMorePages) {
                    listLoader.value = true
                    incrementPage()

                    delay(500) // todo remove this later
                    // extra safety check to not accidentally call when the screen first loads
                    if (page.value > 1) {
                        appendCards(fetchCards())
                    }
                }
            } catch (exception: Exception) {
                cancel()
                listLoader.value = false
                nextPageLoadError.value = ErrorMessage(
                    titleRes = R.string.unown_error_title,
                    bodyRes = R.string.unown_error_body
                )
            }
        }
        fetchNextPageJob?.invokeOnCompletion {
            listLoader.value = false
            it?.let {
                fetchNextPageJob?.cancel()
                nextPageLoadError.value = ErrorMessage(
                    titleRes = R.string.unown_error_title,
                    bodyRes = R.string.unown_error_body
                )
            }
        }
    }

    private suspend fun fetchCards(): List<HearthstoneCard> {
        val result = mockRepository.fetchCards(
            category = selectedCategory.value.value,
            cursor = cursor,
            limit = PAGE_SIZE
        )
        hasMorePages = result.hasMoreItems()
        cursor = result.meta.pagination.cursor
        return result.data
    }

    fun refresh() {
        resetError()
        resetPagination()
        firstPage()
    }

    fun onCategorySelected(item: CardSetCategory) {
        this.selectedCategory.value = item
        clearList()
        resetError()
        resetPagination()
        firstPage()
    }

    fun toggleGridLayout() {
        this.gridLayout.value = !this.gridLayout.value
    }

    fun clearList() {
        this.cards.value = listOf()
    }

    private fun appendCards(cards: List<HearthstoneCard>) {
        val current = ArrayList(this.cards.value)
        current.addAll(cards)
        this.cards.value = current
    }

    fun onChangeListScrollPosition(position: Int) {
        this.listScrollPosition = position
    }

    private fun resetError() {
        this.initialLoadError.value = null
    }

    private fun resetPagination() {
        this.cursor = "true"
        this.page.value = 1
        onChangeListScrollPosition(0)
    }

    private fun incrementPage() {
        this.page.value += 1
    }

    private fun cancelAllJobs() {
        fetchFirstPageJob?.cancel()
        fetchNextPageJob?.cancel()
    }

    fun setFirstVisibleListItemPosition(position: Int) {
        firstVisibleListIndex = position
    }
}