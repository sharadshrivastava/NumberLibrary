package com.test.app.ui.numbers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.app.databinding.LayoutNumberItemBinding
import com.test.numberslib.data.cache.entity.Data

class NumbersAdapter : ListAdapter<Data, NumbersAdapter.NumberViewHolder>(NumberDiffCallback) {

    class NumberViewHolder(private val binding: LayoutNumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.number.text = data.value.toString()
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

object NumberDiffCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.value == newItem.value
    }
}