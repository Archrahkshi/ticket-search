package com.archrahkshi.ticketsearch.ui

import android.content.Context
import com.archrahkshi.ticketsearch.R
import java.text.NumberFormat
import java.util.Locale

const val DEPARTURE_TEXT_KEY = "SAVED_DEPARTURE_TEXT_KEY"
const val DESTINATION_TEXT_KEY = "SAVED_DESTINATION_TEXT_KEY"

fun applyPriceTemplate(context: Context, price: Int) = context.getString(
    R.string.price_template,
    NumberFormat.getInstance(Locale("ru")).apply {
        isGroupingUsed = true
    }.format(price).replace(' ', '\u00A0')
)
