package com.honeycomb.omdbapp.presentation.display

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.databinding.FragmentSeriesBinding
import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity
import com.honeycomb.omdbapp.presentation.display.adapter.MovieOrSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SeriesFragment : Fragment(R.layout.fragment_series), IItemClick {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var displayViewModel: DisplayViewModel
    private lateinit var movieOrSeriesAdapter : MovieOrSeriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeriesBinding.bind(view)

        displayViewModel = ViewModelProvider(requireActivity()).get(DisplayViewModel::class.java)
        observeSeries()
        observeState()
    }

    private fun observeState(){
        displayViewModel.mState
            .flowWithLifecycle (viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleState(state)
            }
            .launchIn (viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: DisplayState){
        when(state){
            is DisplayState.IsError -> handleLoading(state.isError)
            is DisplayState.Init -> Unit
        }
    }
    private fun handleLoading(isError: Boolean){
        if(isError){
            binding.seriesRecyclerview.visibility = View.INVISIBLE
            binding.notFoundLayout.visibility = View.VISIBLE
        }else{
            binding.seriesRecyclerview.visibility = View.VISIBLE
            binding.notFoundLayout.visibility = View.INVISIBLE
        }
    }

    private fun observeSeries(){
        displayViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { movies ->
                if(movies != null){
                    val moviesList = mutableListOf<SearchItemEntity>()
                    movies.Search.forEach { item ->
                        if(item.type == "series"){
                            moviesList.add(SearchItemEntity(item.title,item.year,item.imdbID,item.type,item.poster))
                        }
                    }
                    movieOrSeriesAdapter = MovieOrSeriesAdapter(moviesList,this)

                    binding.seriesRecyclerview.apply {
                        adapter = movieOrSeriesAdapter
                        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun itemClicked(usersEntity: SearchItemEntity) {
        val action = DisplayFragmentDirections.actionDisplayFragmentToDetailFragment(usersEntity)
        requireView().findNavController().navigate(action)
    }
}