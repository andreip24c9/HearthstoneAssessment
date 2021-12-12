package com.andrei.hearthstoneassesment.presentation.ui.list

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.domain.model.HearthstoneCardList
import com.andrei.hearthstoneassesment.presentation.components.*
import com.andrei.hearthstoneassesment.presentation.ui.list.HearthstoneCardListViewModel.Companion.PAGE_SIZE
import com.andrei.hearthstoneassesment.presentation.ui.theme.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HearthstoneCardListFragment : Fragment() {

    private val viewModel: HearthstoneCardListViewModel by viewModels()

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val scaffoldState = rememberScaffoldState()
                val systemUiController = rememberSystemUiController()
                SideEffect { systemUiController.setSystemBarsColor(color = HSDarkRed) }
                MaterialTheme {
                    val cards = viewModel.cards.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val pageLoading = viewModel.pageLoader.value
                    val listLoading = viewModel.listLoader.value
                    val page = viewModel.page.value
                    val showAsGrid = viewModel.gridLayout.value
                    val initialLoadError = viewModel.initialLoadError.value

                    Scaffold(
                        topBar = {
                            ImageToolbar(
                                imageRes = R.drawable.hearthstone_text_logo,
                                secondMenuIconRes = if (showAsGrid) R.drawable.ic_grid else R.drawable.ic_list,
                                onInfoClicked = { findNavController().navigate(R.id.action_view_info) },
                                onSecondMenuItemClicked = { viewModel.toggleGridLayout() }
                            )
                        },
                        scaffoldState = scaffoldState
                    ) {
                        Column {
                            HorizontalList(
                                listItems = getAllCardCategories(),
                                modifier = Modifier.background(color = HSPaleYellow),
                                items = { index, item ->
                                    CardSetChip(
                                        text = item.value,
                                        selected = selectedCategory == item,
                                    ) { viewModel.onCategorySelected(item) }
                                })

                            Box(modifier = Modifier.background(color = HSPaleYellow)) {
                                SwipeRefresh(
                                    state = SwipeRefreshState(isRefreshing = pageLoading && cards.isNotEmpty()),
                                    onRefresh = { viewModel.refresh() },
                                    indicator = { state, trigger ->
                                        SwipeRefreshIndicator(
                                            state = state,
                                            refreshTriggerDistance = trigger,
                                            scale = true,
                                            backgroundColor = HSGoldYellow2
                                        )
                                    }) {

                                    // for grid set the initial scroll index to:
                                    // index / no of columns in the grid
                                    val scrollState =
                                        rememberLazyListState(
                                            if (showAsGrid)
                                                viewModel.firstVisibleListIndex / (
                                                        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 4
                                                        else 2)
                                            else
                                                viewModel.firstVisibleListIndex
                                        )

                                    if (showAsGrid) {
                                        ScrollToTopGridView(
                                            lazyListState = scrollState,
                                            listItems = cards,
                                            withLoader = listLoading,
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .background(color = HSPaleYellow),
                                            items = { index, item ->
                                                viewModel.onChangeListScrollPosition(index)
                                                if ((index + 1) >= (page * PAGE_SIZE) && !(pageLoading || listLoading)) {
                                                    viewModel.nextPage()
                                                }
                                                HearthstoneCardGridViewItem(
                                                    hearthstoneCard = item,
                                                    onClick = {
                                                        viewModel.setFirstVisibleListItemPosition(
                                                            index
                                                        )
                                                        val bundle = Bundle()
                                                        bundle.putParcelable(
                                                            "cards",
                                                            HearthstoneCardList(cards)
                                                        )
                                                        bundle.putInt("index", index)
                                                        findNavController().navigate(
                                                            R.id.action_view_detail,
                                                            bundle
                                                        )
                                                    })
                                            }
                                        )
                                    } else {
                                        VerticalScrollToTopList(
                                            lazyListState = scrollState,
                                            listItems = cards,
                                            withLoader = listLoading,
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .background(color = HSPaleYellow),
                                            items = { index, item ->
                                                viewModel.onChangeListScrollPosition(index)
                                                if ((index + 1) >= (page * PAGE_SIZE) && !(pageLoading || listLoading)) {
                                                    viewModel.nextPage()
                                                }
                                                HearthstoneCardListViewItem(
                                                    hearthstoneCard = item,
                                                    onClick = {
                                                        viewModel.setFirstVisibleListItemPosition(
                                                            index
                                                        )
                                                        val bundle = Bundle()
                                                        bundle.putParcelable(
                                                            "cards",
                                                            HearthstoneCardList(cards)
                                                        )
                                                        bundle.putInt("index", index)
                                                        findNavController().navigate(
                                                            R.id.action_view_detail,
                                                            bundle
                                                        )
                                                    })
                                                Divider(color = HSGoldYellow)
                                            }
                                        )
                                    }
                                }
                                if ((pageLoading && cards.isEmpty()) || initialLoadError != null) {
                                    LoadingErrorView(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        isLoading = pageLoading,
                                        error = initialLoadError,
                                        retryButton = initialLoadError?.let {
                                            RetryButton(
                                                icon = R.drawable.ic_refresh,
                                                textRes = R.string.retry_btn,
                                                onClick = { viewModel.refresh() })
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}