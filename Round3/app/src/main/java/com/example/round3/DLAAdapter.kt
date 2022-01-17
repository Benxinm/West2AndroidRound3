package com.example.round3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.round3.member.DLA

class DLAAdapter(public val DLAList:ArrayList<DLA>):RecyclerView.Adapter<DLAAdapter.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val profileImage:ImageView=view.findViewById(R.id.DLAProfile)
        val upId:TextView=view.findViewById(R.id.dlaID)
        val DLAImageView:ImageView=view.findViewById(R.id.DLAImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.dla_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val DLA=DLAList[position]
        holder.profileImage.setImageResource(DLA.profile.imageId)
        holder.upId.text=DLA.profile.name
        holder.DLAImageView.setImageResource(DLA.DLAImageId)
    }

    override fun getItemCount(): Int=DLAList.size
}