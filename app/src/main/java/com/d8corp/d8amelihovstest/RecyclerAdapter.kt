package com.d8corp.d8amelihovstest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d8corp.d8amelihovstest.RecyclerAdapter.MyViewHolder

class RecyclerAdapter(items: List<ListItem>) : RecyclerView.Adapter<MyViewHolder>() {

    val items: MutableList<ListItem> = ArrayList()

    init {
        this.items.addAll(items)
    }

    fun updateData(data: List<ListItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val examItem = items[position]

        holder.title.text = examItem.title
        holder.subTitle.text = examItem.subTitle
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var subTitle: TextView = itemView.findViewById(R.id.subtitle)
    }
}
