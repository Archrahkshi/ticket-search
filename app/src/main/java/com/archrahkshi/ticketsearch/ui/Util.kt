package com.archrahkshi.ticketsearch.ui

import android.content.Context
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.getDefaultLocale
import java.text.NumberFormat

const val DEPARTURE_TEXT_KEY = "DEPARTURE_TEXT_KEY"
const val DESTINATION_TEXT_KEY = "DESTINATION_TEXT_KEY"
const val DEPARTURE_DATE_KEY = "DEPARTURE_DATE_KEY"
const val PASSENGER_COUNT_KEY = "PASSENGER_COUNT_KEY"

fun applyPriceTemplate(context: Context, price: Int) = context.getString(
    R.string.price_template,
    NumberFormat.getInstance(getDefaultLocale()).apply {
        isGroupingUsed = true
    }.format(price).replace(' ', '\u00A0')
)
