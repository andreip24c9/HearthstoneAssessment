package com.andrei.hearthstoneassessment.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.andrei.hearthstoneassessment.databinding.MainActivityBinding
import com.andrei.hearthstoneassessment.presentation.ui.list.HearthstoneCardListFragment
import dagger.hilt.android.AndroidEntryPoint

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