package com.example.myexperiment.experiments.recycler_binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myexperiment.databinding.MovieItemViewBinding
import com.example.myexperiment.experiments.recycler_binding.model.Movie

/**
 * Created by nampham on 4/17/21.
 */
class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffUtilCallback()) {

    interface MovieAdapterListener {
        fun onClickItem(movie: Movie)
    }

    var listener: MovieAdapterListener? = null

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(view: View, private val binding: MovieItemViewBinding) :
        RecyclerView.ViewHolder(view) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding.root, binding)
            }
        }

        fun bind(item: Movie, listener: MovieAdapterListener?) {
            binding.item = item
            itemView.setOnClickListener {
                listener?.onClickItem(item)
            }
        }
    }

}