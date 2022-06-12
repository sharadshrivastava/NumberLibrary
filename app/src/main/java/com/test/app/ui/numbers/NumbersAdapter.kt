package com.test.app.ui.numbers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.app.databinding.LayoutNumberItemBinding

class NumbersAdapter : ListAdapter<Int, NumbersAdapter.NumberViewHolder>(NumberDiffCallback) {

    class NumberViewHolder(private val binding: LayoutNumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Int) {
            binding.number.text = value.toString()
        }
    }

    /* Creates and inflates view and return NumberViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NumberViewHolder(
            LayoutNumberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    /* Gets current number and uses it to bind view. */
    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) =
        holder.bind(getItem(position))

}

object NumberDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}