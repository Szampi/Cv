package com.hermanowicz.cv.screens.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.CvData
import kotlinx.android.synthetic.main.i_title.view.*

class TitleAdapter : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val list = mutableListOf<CvData>()
    private lateinit var adapter: SubtitleAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        return TitleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.i_title, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(categories: List<CvData>) {
        list.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        val currentTitle = list[position]
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            currentTitle.expanded = !currentTitle.expanded
            notifyItemChanged(position)
        }
    }

    inner class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CvData) {
            adapter = SubtitleAdapter()
            adapter.addItems(item.subtitles)
            layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            itemView.subtitleRecycler.adapter = adapter
            itemView.subtitleRecycler.layoutManager = layoutManager
            val expanded = item.expanded
            itemView.expandableLayout.visibility = if (expanded) View.VISIBLE else View.GONE

            itemView.title.text = item.title
        }
    }
}


