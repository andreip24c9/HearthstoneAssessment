package com.andrei.hearthstoneassesment.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.andrei.hearthstoneassesment.R
import com.andrei.hearthstoneassesment.databinding.MainActivityBinding
import com.andrei.hearthstoneassesment.domain.util.sdk28AndUp
import com.andrei.hearthstoneassesment.presentation.ui.list.HearthstoneCardListFragment
import com.andrei.hearthstoneassesment.presentation.ui.list.HearthstoneCardListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if ((binding.mainNavHostFragment.findNavController().currentBackStackEntry?.destination as? FragmentNavigator.Destination)?.className == HearthstoneCardListFragment::class.java.name) super.onBackPressed()
        else binding.mainNavHostFragment.findNavController().popBackStack()
    }
}