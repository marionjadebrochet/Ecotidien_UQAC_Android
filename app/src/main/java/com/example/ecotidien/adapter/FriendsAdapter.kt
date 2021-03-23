package com.example.ecotidien.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecotidien.R
import com.example.ecotidien.model.Friends

class FriendsAdapter(private val dataset: List<Friends>) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    class FriendViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {
        val username_text_view: TextView = view.findViewById(R.id.friend_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.friend_item_layout, parent, false)
        return FriendViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = dataset[position]
        holder.username_text_view.text = friend.username
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}