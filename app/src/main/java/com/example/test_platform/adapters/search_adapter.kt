package com.example.test_platform.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_platform.data_class.search.data
import com.example.test_platform.R

class search_adapter(val context:Activity,val searchList:List<data>):
    RecyclerView.Adapter<search_adapter.MyviewHolder>()
{
    class MyviewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val cover:ImageView
        val title:TextView
        val artists:TextView

        init{
            cover=itemView.findViewById(R.id.cover)
            title=itemview.findViewById(R.id.title)
            artists=itemview.findViewById(R.id.artists)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        return search_adapter.MyviewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val Current_data=position

    }

}