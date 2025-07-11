package com.example.test_platform.adapters

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.test_platform.data_class.albumx
import com.example.test_platform.fragments.album_tracks
import com.example.test_platform.R
import com.squareup.picasso.Picasso


class albs_adapter(val context: Activity, val AlbumsList:List<albumx>):
    RecyclerView.Adapter<albs_adapter.MyViewHolder>() {
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        val cover:ImageView
        val albname:TextView

        init{
            cover=itemView.findViewById(R.id.cover_img)
            albname=itemView.findViewById(R.id.name_album)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_albums, parent, false)
        return albs_adapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return AlbumsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cData = AlbumsList[position]

        holder.albname.text=cData.name

        cData.images.firstOrNull()?.url?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.cover)
        }

        holder.itemView.setOnClickListener {
            val fragment = album_tracks()
            val bundle = Bundle()
            bundle.putString("album_id",cData.id)
            bundle.putString("album_name", cData.name)
            bundle.putString("album_image", cData.images.firstOrNull()?.url)
            fragment.arguments = bundle

            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }

}