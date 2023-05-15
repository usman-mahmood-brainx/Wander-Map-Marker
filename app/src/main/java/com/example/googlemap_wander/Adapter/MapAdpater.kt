package com.example.googlemap_wander.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemap_wander.Models.UserMap
import com.example.googlemap_wander.R


class MapAdpater(private var mapList: List<UserMap>, val onClickListener: OnClickListener) : RecyclerView.Adapter<MapAdpater.ViewHolder>() {

    interface OnClickListener{
        fun onItemClick(userMap: UserMap,position: Int)

    }

    fun setList(list : List<UserMap>){
        mapList = list
        notifyDataSetChanged()
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.map_title_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val userMap = mapList[position]

        holder.tvTitle.text = userMap.title
        holder.tvTitle.setOnClickListener {
            println("Tapped on position : $position")
            onClickListener.onItemClick(userMap,position)
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mapList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(MapDataView: View) : RecyclerView.ViewHolder(MapDataView) {
        val tvTitle: TextView = MapDataView.findViewById(R.id.tvTitle)


    }
}