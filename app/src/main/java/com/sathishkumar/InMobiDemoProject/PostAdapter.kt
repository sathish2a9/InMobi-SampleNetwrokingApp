package com.sathishkumar.InMobiDemoProject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.posts_item.view.*

class PostAdapter(val nameList : List<Posts>) : RecyclerView.Adapter<PostAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.posts_item, parent, false)
        return NameViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: PostAdapter.NameViewHolder, position: Int) {
        val names = nameList[position]
        holder.itemView.tv_id.text = names.id.toString()
        holder.itemView.body.text = names.body.toString()
        holder.itemView.title.text = names.title
    }


    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}