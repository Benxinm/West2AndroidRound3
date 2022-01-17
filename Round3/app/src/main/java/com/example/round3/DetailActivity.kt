package com.example.round3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.round3.member.Profile
import com.example.round3.member.TransferPara
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var position:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val profile=intent.getParcelableExtra<Profile>("profile")
        DetailCycleImage.setImageResource(profile!!.imageId)
        DetailName.text=profile!!.name
        fansNum.text=profile!!.fanNum
        val profileList=intent.getParcelableArrayListExtra<Profile>("profileList")
        DetailButton.setOnClickListener {
            Toast.makeText(this,"取消关注成功",Toast.LENGTH_SHORT).show()
            position=profileList!!.indexOf(profile)
            finish()
        }
    }
    override fun finish() {
        super.finish()
        TransferPara.list.add(position)
    }
}