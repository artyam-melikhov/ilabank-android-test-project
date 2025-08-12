package com.d8corp.d8amelihovstest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PagerAdapter internal constructor(private val context: Context, private val size: Int) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.page, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.images.setImageResource(R.drawable.test)
        holder.pageNumber.text = "" + (position + 1)
    }

    override fun getItemCount(): Int = size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ImageView = itemView.findViewById(R.id.image)
        var pageNumber: TextView = itemView.findViewById(R.id.page_number)
    }
}
