package com.example.round3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.round3.member.DLA
import com.example.round3.member.Profile
import com.example.round3.member.TransferPara
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var position:Int=-1
    var profileList=ArrayList<Profile>()
    val dlaList=ArrayList<DLA>()
    val upAccount=HashMap<Profile,ArrayList<DLA>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initProfiles()
        initDLAs()
        val layOutManager=LinearLayoutManager(this)
        val layOutManager_=LinearLayoutManager(this)
        DLARecyclerView.layoutManager=layOutManager_
        layOutManager.orientation=LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager=layOutManager

        val adapterDLA=DLAAdapter(dlaList)
        val adapter=PictureAdapter(profileList, window.decorView,upAccount,this)
        if (position>0){
            adapter.removeList(position)
        }
        recyclerView.adapter=adapter
        DLARecyclerView.adapter=adapterDLA
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }
    private fun refreshFruits(adapter: PictureAdapter){
        thread {
            Thread.sleep(500)
            runOnUiThread{

                adapter.notifyDataSetChanged()
                if (TransferPara.list!=null){
                    correct(TransferPara.list)
                    for (tmp in TransferPara.list){
                        adapter.removeList(tmp)
                    }
                    TransferPara.list.clear()
                }
                swipeRefresh.isRefreshing = false
            }
        }
    }
    val old_tomato=Profile("老番茄",R.drawable.old_tomato,"1675.2万")
    val luo_xiang=Profile("罗翔老师",R.drawable.luo_xiang,"2051.8万")
    val ban_fo=Profile("硬核的半佛仙人",R.drawable.ban_fo,"638.6万")
    val xiao_jie=Profile("超级小桀",R.drawable.xieo_jie,"78.2万")
    val que_chao=Profile("逗逼的雀巢",R.drawable.que_chao,"465.4万")
    private fun initProfiles(){
        profileList.add(old_tomato)
        upAccount.put(old_tomato, ArrayList())
        profileList.add(luo_xiang)
        upAccount.put(luo_xiang, ArrayList())
        profileList.add(ban_fo)
        upAccount.put(ban_fo, ArrayList())
        profileList.add(xiao_jie)
        upAccount.put(xiao_jie, ArrayList())
        profileList.add(que_chao)
        upAccount.put(que_chao, ArrayList())
    }
    private fun initDLAs(){
        grouping(old_tomato,R.drawable.dla01)
        grouping(luo_xiang,R.drawable.dla02)
        grouping(ban_fo,R.drawable.dla03)
        grouping(xiao_jie,R.drawable.dla04)
        grouping(que_chao,R.drawable.dla05)
        grouping(que_chao,R.drawable.dla06)
    }
    private fun grouping(up:Profile,imageId:Int){
        val tmp=DLA(up,imageId)
        dlaList.add(tmp)
        upAccount.get(tmp.profile)?.add(tmp)
    }
    private fun correct(list:ArrayList<Int>){
        list.sort()
        for(i in list.indices){
            list[i]=list[i]-i
        }
    }
}

