package com.honeycomb.omdbapp.presentation.display

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.databinding.FragmentMoviesBinding
import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity
import com.honeycomb.omdbapp.presentation.display.adapter.MovieOrSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies), IItemClick {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var displayViewModel: DisplayViewModel
    private lateinit var movieOrSeriesAdapter : MovieOrSeriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)

        displayViewModel = ViewModelProvider(requireActivity()).get(DisplayViewModel::class.java)
        observeMovies()
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
            binding.moviesRecyclerview.visibility = View.INVISIBLE
            binding.notFoundLayout.visibility = View.VISIBLE
        }else{
            binding.moviesRecyclerview.visibility = View.VISIBLE
            binding.notFoundLayout.visibility = View.INVISIBLE
        }
    }
    private fun observeMovies(){
        displayViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { movies ->
                if(movies != null){
                    val moviesList = mutableListOf<SearchItemEntity>()
                    movies.Search.forEach { item ->
                        if(item.type == "movie"){
                            moviesList.add(SearchItemEntity(item.title,item.year,item.imdbID,item.type,item.poster))
                        }
                    }
                    movieOrSeriesAdapter = MovieOrSeriesAdapter(moviesList,this)

                    binding.moviesRecyclerview.apply {
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