package com.honeycomb.omdbapp.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.databinding.FragmentDetailBinding
import com.honeycomb.omdbapp.databinding.FragmentDisplayBinding
import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity
import com.honeycomb.omdbapp.presentation.detail.viewmodel.DetailViewModel
import com.honeycomb.omdbapp.presentation.display.DisplayFragmentDirections
import com.honeycomb.omdbapp.presentation.display.DisplayViewModel
import com.honeycomb.omdbapp.presentation.display.adapter.MovieOrSeriesAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_or_series_item.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailViewModel: DetailViewModel

    val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        detailViewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
        detailViewModel.fetchItem(args.itemDetail!!.imdbID)
        observeItem()

        binding.itemPlot.movementMethod = ScrollingMovementMethod()
        binding.back.setOnClickListener {
            requireView().findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToDisplayFragment())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeItem(){
        detailViewModel.mUsers
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { item ->
                if(item != null){
                    Picasso.get().load(item.poster).into(binding.itemImage)
                    binding.itemName.text = item.title
                    binding.itemTime.text = item.runtime
                    binding.itemStar.text = "${item.imdbRating} (IMDB)"
                    binding.itemReleased.text = item.released
                    binding.itemGenre.text = item.genre
                    binding.itemPlot.text = item.plot
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}