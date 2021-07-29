package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CollegeDataAdapter(
    private val exampleList: List<CollegeData>,
    private val listener: MainActivity
    ) :
        RecyclerView.Adapter<CollegeDataAdapter.ExampleViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item,
                parent, false)
            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = exampleList[position]
            holder.textView1.text=currentItem.country
            holder.textView2.text = currentItem.name
            holder.textView3.text = currentItem.web_pages[0] as String
        }
        override fun getItemCount() = exampleList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val textView1: TextView = itemView.findViewById(R.id.countryName)
        val textView2: TextView = itemView.findViewById(R.id.college_name)
        val textView3: TextView = itemView.findViewById(R.id.website)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                if (v != null) {
                    listener.onItemClick(textView3.text.toString())
                }
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(url:String)
    }
    }