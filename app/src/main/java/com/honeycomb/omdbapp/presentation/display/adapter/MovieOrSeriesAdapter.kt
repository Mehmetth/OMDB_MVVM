package com.honeycomb.omdbapp.presentation.display.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.omdbapp.R
import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity
import com.honeycomb.omdbapp.presentation.display.IItemClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_or_series_item.view.*

class MovieOrSeriesAdapter(var searchItemEntityList: List<SearchItemEntity>,
                           val iItemClick: IItemClick,):
    RecyclerView.Adapter<MovieOrSeriesAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_or_series_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchItemEntityList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(searchItemEntityList[position].poster).into(holder.view.movie_image)
        holder.view.movie_name.text = "${searchItemEntityList[position].title} (${searchItemEntityList[position].year})"

        holder.itemView.setOnClickListener {
            iItemClick.itemClicked( searchItemEntityList[position])
        }
    }
}