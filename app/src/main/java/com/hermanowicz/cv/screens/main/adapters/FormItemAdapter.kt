package com.hermanowicz.cv.screens.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hermanowicz.cv.R
import com.hermanowicz.cv.model.FormItem
import com.hermanowicz.cv.utils.view.formatDate
import com.hermanowicz.cv.utils.view.load
import kotlinx.android.synthetic.main.i_todo.view.*

class FormItemAdapter(private val listener: FormItemClickedListener? = null) :
    RecyclerView.Adapter<FormItemAdapter.TitleViewHolder>() {

    companion object {
        private const val MIN_LINES = 2
        private const val MAX_LINES = 20
    }

    private val list = mutableListOf<FormItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        return TitleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.i_todo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(categories: List<FormItem>) {
        list.clear()
        list.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun removeItem(position: Int) {
        list.remove(list[position])
        notifyItemRemoved(position)
    }

    inner class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: FormItem) {
            var currentMaxLines = itemView.description.maxLines
            itemView.title.text = item.title
            itemView.description.text = item.description
            itemView.image.load(itemView.context, item.url)

            if (item.description.length < 50) itemView.expandableImg.visibility =
                View.GONE else itemView.expandableImg.visibility = View.VISIBLE

            itemView.itemContainer.setOnClickListener {
                listener?.onFormItemClicked(item, adapterPosition)
            }
            itemView.itemContainer.setOnLongClickListener {
                listener?.onFormLongClick(adapterPosition)
                true
            }
            itemView.expandableImg.setOnClickListener {
                if (currentMaxLines == MIN_LINES) {
                    itemView.description.maxLines = MAX_LINES
                    currentMaxLines = MAX_LINES
                    itemView.expandableImg.setBackgroundResource(R.drawable.ic_arrow_up_24dp)
                } else {
                    itemView.description.maxLines = MIN_LINES
                    currentMaxLines = MIN_LINES
                    itemView.expandableImg.setBackgroundResource(R.drawable.ic_arrow_down_24dp)
                }
            }

            itemView.date.text = item.date.formatDate()
        }
    }
}

interface FormItemClickedListener {
    fun onFormItemClicked(item: FormItem, position: Int)
    fun onFormLongClick(adapterPosition: Int)
}


