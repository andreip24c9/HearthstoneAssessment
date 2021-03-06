package com.andrei.hearthstoneassessment.presentation.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andrei.hearthstoneassessment.presentation.components.Toolbar
import com.andrei.hearthstoneassessment.presentation.theme.HSAppTheme

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HSAppTheme {
                    Scaffold(
                        topBar = {
                            Toolbar(
                                "App info",
                                onNavigationItemClick = { findNavController().popBackStack() },
                                navigationIcon = Icons.Filled.ArrowBack
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(start = 16.dp, end = 16.dp)
                        ) {
                            Text(
                                text = "??? Hearthstone deck builder assessment application by Andrei Petrescu",
                                modifier = Modifier.padding(top = 16.dp),
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.body2
                            )

                            Text(
                                text = "The application is build in Kotlin using the following:",
                                modifier = Modifier.padding(top = 16.dp),
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.h3

                            )
                            Text(
                                text = "??? MVVM pattern\n" +
                                        "??? Coroutines for asynchronous code execution\n" +
                                        "??? Jetpack Compose for UI building\n" +
                                        "??? Navigation Fragment ??? navigation graph\n" +
                                        "??? Hilt for dependency injection\n" +
                                        "??? Retrofit with Okhttp3 for networking\n" +
                                        "??? Coil for asynchronous image & gif loading\n",
                                modifier = Modifier.padding(top = 4.dp),
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}