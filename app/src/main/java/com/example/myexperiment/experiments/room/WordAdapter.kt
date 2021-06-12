package com.example.myexperiment.experiments.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myexperiment.databinding.WordItemBinding
import com.example.myexperiment.experiments.room.db.Word

/**
 * Created by nampham on 5/15/21.
 */

class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WordViewHolder(private val itemViewBinding: WordItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {


        fun bind(item: Word) {
            itemViewBinding.item = item
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val binding = WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return WordViewHolder(binding)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }
}