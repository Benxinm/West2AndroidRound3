package com.example.round3.member

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Profile(val name:String, val imageId:Int ,val fanNum:String):Parcelable {

}