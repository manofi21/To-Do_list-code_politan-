package com.example.to_dopractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainTask(
    val id: Int? = null,
    val title: String? = null,
    val details: String? = null,
    var date: String? = null,
    var isComplete: Boolean = false
): Parcelable