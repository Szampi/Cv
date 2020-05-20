package com.hermanowicz.cv.screens.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hermanowicz.cv.R
import kotlinx.android.synthetic.main.i_subtitle.view.*

class SubtitleAdapter() : RecyclerView.Adapter<SubtitleAdapter.SubcategoryViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        return SubcategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.i_subtitle, parent, false)
        )
    }

    fun addItems(subcategories: List<String>) {
        list.addAll(subcategories)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class SubcategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: String) {
            itemView.title.text = item
        }
    }
}

