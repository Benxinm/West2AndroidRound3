package com.example.round3

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.round3.member.DLA
import com.example.round3.member.Profile
import com.example.round3.member.TransferPara
import android.content.Intent as Intent


class PictureAdapter(val profileList:ArrayList<Profile>, val mainView:View,val upAccount:HashMap<Profile,ArrayList<DLA>>,val mContext:Context):RecyclerView.Adapter<PictureAdapter.ViewHolder>(){
    inner class ViewHolder(view : View):RecyclerView.ViewHolder(view){
        val profileImage:ImageView = view.findViewById(R.id.profile)
        val upId:TextView=view.findViewById(R.id.upId)
    }

    inner class SkipOnLongClicker(val profile: Profile): View.OnLongClickListener{
        var  intent:Intent?=null
        override fun onLongClick(view:View): Boolean {
            intent=Intent(mContext,DetailActivity::class.java)
            intent?.putExtra("profile",profile)
                ?.putExtra("profileList",profileList)
            mContext.startActivity(intent)
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.profile_item,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile=profileList[position]
        val textView:TextView=mainView.findViewById(R.id.textView)
        holder.profileImage.setImageResource(profile.imageId)
        holder.upId.text=profile.name
        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val profile=profileList[position]
            textView.text="${profile.name} 的视频动态"
            val dlaList=upAccount.get(profile)
            val adapterDLA= dlaList?.let { it1 -> DLAAdapter(it1) }
            mainView.findViewById<RecyclerView>(R.id.DLARecyclerView).adapter=adapterDLA
        }
        holder.itemView.setOnLongClickListener(SkipOnLongClicker(profile))
        holder.profileImage.setOnClickListener {
            val position=holder.adapterPosition
            val profile=profileList[position]
            textView.text="${profile.name} 的视频动态"
            val dlaList=upAccount.get(profile)
            val adapterDLA= dlaList?.let { it1 -> DLAAdapter(it1) }
            mainView.findViewById<RecyclerView>(R.id.DLARecyclerView).adapter=adapterDLA
        }
        holder.profileImage.setOnLongClickListener(SkipOnLongClicker(profile))
    }

    override fun getItemCount()=profileList.size
    fun removeList(position:Int){
        profileList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount);
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            1->if (requestCode== RESULT_OK){
//                val returnedData=data?.getIntExtra("position",-1)
//                removeList(returnedData!!)
//            }
//        }
//    }
}