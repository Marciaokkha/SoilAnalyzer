package com.example.soilanalyzer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Analyze(
    val analyze: String
) : Parcelable