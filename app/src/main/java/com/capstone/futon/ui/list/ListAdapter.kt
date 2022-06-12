package com.capstone.futon.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.futon.data.model.ListItem
import com.capstone.futon.data.model.Plant
import com.capstone.futon.databinding.ItemListBinding

class ListAdapter: androidx.recyclerview.widget.ListAdapter<Plant, ListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    inner class ListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.tvNama.text = item.name
            Glide.with(itemView.context)
                .load(item.photo)
                .into(binding.ivFoto)
            binding.tvDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem._id == newItem._id
            }
        }
    }
}