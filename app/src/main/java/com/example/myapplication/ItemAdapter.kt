package com.sdei.woundspro.recyclerviewsinglemultiple

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.Item
import com.example.myapplication.R
import com.sdei.woundspro.recyclerviewsinglemultiple.ItemAdapter.RecyclerViewHolder
import java.util.*

class ItemAdapter(var context: Context, receiver: OnClickAction) : RecyclerView.Adapter<RecyclerViewHolder>() {

    var inflater: LayoutInflater
    var items: MutableList<Item>
    private var selected: MutableList<Item>
    var receiver: OnClickAction

    inner class RecyclerViewHolder(view: View) : ViewHolder(view) {
        var tv: TextView
        init {
            tv = itemView.findViewById<View>(R.id.tv) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = inflater.inflate(R.layout.recycle_item, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.tv.text = item.title
        holder.tv.tag = holder
        holder.itemView.setOnClickListener {

            if (selected.contains(item)) {
                selected.remove(item)
                unhighlightView(holder)
//single unselect  and list remove
                receiver.onClickAction(position, getSelected())
            } else {
                selected.add(item)
                highlightView(holder)
//single select and list add
                receiver.onClickAction(position, getSelected())
            }
        }
        if (selected.contains(item)) {
            //1.by default run this for check all
            highlightView(holder)
            receiver.onClickAction(position, getSelected())
        } else {
            //1.by default run this for uncheck all
            unhighlightView(holder)
            receiver.onClickAction(position, getSelected())
        }
    }

    private fun highlightView(holder: RecyclerViewHolder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
    }

    private fun unhighlightView(holder: RecyclerViewHolder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(items: MutableList<Item>) {
        clearAll(false)
        this.items = items
        notifyDataSetChanged()
    }

    fun clearAll(isNotify: Boolean) {
        items.clear()
        selected.clear()
        if (isNotify) notifyDataSetChanged()
    }

    fun clearSelected() {
        selected.clear()
        notifyDataSetChanged()
    }

    fun selectAll() {
        selected.clear()
        selected.addAll(items)
        notifyDataSetChanged()
    }

    fun getSelected(): List<Item> {
        return selected
    }

    init {
        inflater = LayoutInflater.from(context)
        selected = ArrayList()
        items = ArrayList()
        this.receiver = receiver
    }

    interface OnClickAction {
        fun onClickAction(position: Int, item: List<Item>)
    }
}