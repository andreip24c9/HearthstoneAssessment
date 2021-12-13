package com.andrei.hearthstoneassesment.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrei.hearthstoneassesment.domain.model.HearthstoneCardList
import com.andrei.hearthstoneassesment.presentation.components.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.presentation.theme.Black
import com.andrei.hearthstoneassesment.presentation.theme.HSAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HearthstoneCardDetailFragment : Fragment() {

    private val viewModel: HearthstoneCardDetailsViewModel by viewModels()

    @InternalCoroutinesApi
    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        arguments?.let {
            viewModel.cards.value =
                (it.getParcelable("cards") as? HearthstoneCardList)?.cardList ?: listOf()
            viewModel.currentPage.value =
                if (it.getInt("index") <= viewModel.cards.value.size - 1) it.getInt("index") else 0
        }

        return ComposeView(requireContext()).apply {
            setContent {
                val systemUiController = rememberSystemUiController()

                SideEffect { systemUiController.setSystemBarsColor(color = Black) }
                HSAppTheme {
                    Scaffold {
                        Box(
                            Modifier
                                .fillMaxHeight()
                                .background(color = Black)
                        ) {
                            val currentPage = viewModel.currentPage.value
                            val cards = viewModel.cards.value
                            val firstPage = currentPage == 0
                            val lastPage = currentPage == cards.size - 1
                            val coroutineScope = rememberCoroutineScope()

                            val pagerState = PagerState(currentPage = currentPage)
                            HorizontalPager(
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                state = pagerState,
                                count = cards.size,
                                contentPadding = PaddingValues(horizontal = 0.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) { page ->
                                if (pagerState.currentPage != currentPage && pagerState.pageCount != 0) {
                                    viewModel.setCurrentPage(pagerState.currentPage)
                                }
                                // Our page content
                                PageDetailView(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    card = cards[page]
                                )
                            }
                            val horizontalArrangement =
                                if (!firstPage && !lastPage) Arrangement.SpaceBetween
                                else if (firstPage) Arrangement.End
                                else Arrangement.Start
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 200.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = horizontalArrangement
                            ) {
                                if (!firstPage) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_arrow_left),
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondaryVariant),
                                        modifier = Modifier
                                            .width(48.dp)
                                            .height(48.dp)
                                            .selectable(selected = false, onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(page = viewModel.currentPage.value - 1)
                                                }
                                            }),
                                        contentDescription = null,
                                    )
                                }
                                if (!lastPage) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_arrow_right),
                                        modifier = Modifier
                                            .width(48.dp)
                                            .height(48.dp)
                                            .selectable(selected = false, onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(page = viewModel.currentPage.value + 1)
                                                }
                                            }),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondaryVariant)
                                    )
                                }
                            }
                        }
                        Toolbar(
                            backgroundColor = Color.Transparent,
                            navigationIcon = Icons.Filled.Close,
                            elevation = 0.dp,
                            onNavigationItemClick = { findNavController().popBackStack() })
                    }
                }
            }
        }
    }
}