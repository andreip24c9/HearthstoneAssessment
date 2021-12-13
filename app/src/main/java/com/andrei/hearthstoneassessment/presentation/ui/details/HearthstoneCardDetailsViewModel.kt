package com.andrei.hearthstoneassessment.presentation.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HearthstoneCardDetailsViewModel
@Inject constructor() : ViewModel() {

    val cards: MutableState<List<HearthstoneCard>> = mutableStateOf(ArrayList())
    val currentPage = mutableStateOf(0)

    init {
        this.cards.value = mutableListOf()
        this.currentPage.value = 0
    }

    fun setCurrentPage(page: Int) {
        currentPage.value = page
    }
}