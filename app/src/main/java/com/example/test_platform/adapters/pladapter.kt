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

class pladapter(val context: Activity, val Playlist:List<albumx>):
    RecyclerView.Adapter<pladapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img:ImageView
        val titl:TextView

        init {
            img=itemView.findViewById(R.id.playlist_img)
            titl=itemView.findViewById(R.id.playlist_name)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pladapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_playlists, parent, false)
        return pladapter.MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Playlist.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData=Playlist[position]
        holder.titl.text=currentData.name

        currentData.images.firstOrNull()?.url?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.img)
        }
        holder.itemView.setOnClickListener {
            val fragment = album_tracks()
            val bundle = Bundle()
            bundle.putString("album_id",currentData.id)
            bundle.putString("album_name", currentData.name)
            bundle.putString("album_image", currentData.images.firstOrNull()?.url)
            fragment.arguments = bundle

            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }

}