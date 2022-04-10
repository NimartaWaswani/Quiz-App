package com.example.quizapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultDataClass(val question:Int, val result:Int):Parcelable