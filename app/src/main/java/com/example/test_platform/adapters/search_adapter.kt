package com.example.test_platform.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_platform.R
import com.example.test_platform.data_class.search.item
import com.example.test_platform.data_class.search.datax
import com.squareup.picasso.Picasso

class search_adapter(
    val context: Activity,
    private val searchList: List<item>,
    private val onItemClick: (datax) -> Unit // <-- add this
) : RecyclerView.Adapter<search_adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cover: ImageView = itemView.findViewById(R.id.cover)
        val title: TextView = itemView.findViewById(R.id.title)
        val artists: TextView = itemView.findViewById(R.id.artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trackData = searchList[position].data

        holder.title.text = trackData.name
        holder.artists.text = trackData.artists.items.joinToString(", ") { it.profile.name }

        val imageUrl = trackData.albumoftrack?.coverArt?.sources?.firstOrNull()?.url
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get().load(imageUrl).into(holder.cover)
        } else {
            holder.cover.setImageResource(R.drawable.music_note_24)
        }

        holder.itemView.setOnClickListener {
            onItemClick(trackData)
        }
    }



    override fun getItemCount(): Int = searchList.size

}
