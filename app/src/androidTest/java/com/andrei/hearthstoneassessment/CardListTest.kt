package com.andrei.hearthstoneassessment

import android.os.Bundle
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import com.andrei.hearthstoneassessment.domain.model.HearthstoneCardList
import com.andrei.hearthstoneassessment.presentation.MainActivity
import com.andrei.hearthstoneassessment.presentation.ui.details.HearthstoneCardDetailFragment
import com.andrei.hearthstoneassessment.presentation.ui.list.CardSetCategory
import com.andrei.hearthstoneassessment.presentation.ui.list.HearthstoneCardListViewModel
import com.andrei.hearthstoneassessment.repository.MockHearthstoneCardsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class CardListTest {

    @get: Rule // (order = <order_no>) you can make different rules and this specifies the order
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository: MockHearthstoneCardsRepository

    lateinit var viewModel: HearthstoneCardListViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = HearthstoneCardListViewModel(repository)
    }

    @Test
    fun test_FilterChange() {
        viewModel.onCategorySelected(CardSetCategory.BASIC)
        assertEquals(viewModel.selectedCategory.value, CardSetCategory.BASIC)
    }

    @Test
    fun test_ListPositionChange() {
        viewModel.onChangeListScrollPosition(15)
        assertEquals(viewModel.listScrollPosition, 15)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_openDetailPage() {
        val args = Bundle()
        args.putParcelable("cards", HearthstoneCardList(mutableListOf()))
        args.putInt("index", 0)
        val scenario =
            launchFragmentInHiltContainer<HearthstoneCardDetailFragment>(fragmentArgs = args)
    }

    @Test
    fun mainActivityTest() {
        val scenario = launchActivity<MainActivity>()
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun detailFragmentTest() {
        val scenario = launchFragmentInHiltContainer<HearthstoneCardDetailFragment>()
    }
}