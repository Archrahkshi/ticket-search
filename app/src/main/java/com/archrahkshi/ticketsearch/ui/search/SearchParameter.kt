package com.archrahkshi.ticketsearch.ui.search

import androidx.annotation.DrawableRes

data class SearchParameter(
    @DrawableRes val icon: Int?,
    val title: String,
    val onClick: (() -> Unit)? = null
)
