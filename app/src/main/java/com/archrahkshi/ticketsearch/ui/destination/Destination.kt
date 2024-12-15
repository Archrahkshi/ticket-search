package com.archrahkshi.ticketsearch.ui.destination

import android.graphics.Bitmap

data class Destination(
    val title: String,
    val subtitle: String,
    val image: Bitmap,
    val onClick: (String) -> Unit
)
