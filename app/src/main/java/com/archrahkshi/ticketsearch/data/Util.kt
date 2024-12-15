package com.archrahkshi.ticketsearch.data

import com.archrahkshi.ticketsearch.getDefaultLocale
import java.text.SimpleDateFormat

fun getTimeFromDate(date: String): String =
    SimpleDateFormat("HH:mm", getDefaultLocale()).format(getDateFormat().parse(date)!!)

fun getTimeDifference(start: String, end: String) =
    (getTimeInMillis(end) - getTimeInMillis(start)).let {
        "${it / (1000 * 60 * 60)}:${it / (1000 * 60) % 60}"
    }

private fun getDateFormat() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", getDefaultLocale())

private fun getTimeInMillis(date: String) = getDateFormat().parse(date)!!.time
