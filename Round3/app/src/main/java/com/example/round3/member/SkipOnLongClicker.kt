package com.example.round3.member

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.round3.DetailActivity

class SkipOnLongClicker(mainView: View): View.OnLongClickListener {
    val context_=mainView.context
    override fun onLongClick(view: View?): Boolean {
        val intent= Intent(view?.context,DetailActivity::class.java)
        startActivity(context_,intent, Bundle())
        return true
    }
}