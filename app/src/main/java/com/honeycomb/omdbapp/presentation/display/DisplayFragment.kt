package com.honeycomb.omdbapp.presentation.display

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.databinding.FragmentDisplayBinding
import com.honeycomb.omdbapp.presentation.display.adapter.MyViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : Fragment(R.layout.fragment_display){

    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!

    private lateinit var displayViewModel: DisplayViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDisplayBinding.bind(view)

        displayViewModel = ViewModelProvider(requireActivity()).get(DisplayViewModel::class.java)

        displayViewModel.fetchAllSports(displayViewModel.searchValue)

        val adapter = MyViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(MoviesFragment(), getString(R.string.displayMovies))
        adapter.addFragment(SeriesFragment(), getString(R.string.displaySeries))
        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)

        binding.searchText.setOnEditorActionListener {
                textView, keyCode, keyEvent ->
            val result = textView.text.toString()

            if (keyCode == 5 || keyCode == 6) {
                displayViewModel.searchValue = "*${result.replace("\\s".toRegex(),"%20")}*"
                displayViewModel.fetchAllSports(displayViewModel.searchValue)
            }
            false
        }
    }
    override fun onPause() {
        super.onPause()
        if(binding.viewpager.adapter != null){
            binding.viewpager.adapter = null
            binding.tabs.setupWithViewPager(null)
        }
    }
}