package com.test.app.ui.numbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.app.R

class NumbersAdapter : ListAdapter<Int, NumbersAdapter.NumberViewHolder>(NumberDiffCallback) {

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numberTextView: TextView = itemView.findViewById(R.id.number)

        fun bind(value: Int) {
            numberTextView.text = value.toString()
        }
    }

    /* Creates and inflates view and return NumberViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_number_item, parent, false)
        return NumberViewHolder(view)
    }

    /* Gets current number and uses it to bind view. */
    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object NumberDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}