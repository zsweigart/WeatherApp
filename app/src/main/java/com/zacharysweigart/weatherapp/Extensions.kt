package com.zacharysweigart.weatherapp

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateString(dateFormat: String = "YYYY"): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    return df.format(this)
}

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
    map { Pair(it.key, it.value!!) }.toTypedArray()

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found.")
}

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun View.ctx(): Context = this.context

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat()).withEndAction { visibility = View.GONE }
}

fun View.slideEnter() {
    visibility = View.VISIBLE
    if (translationY < 0f) animate().translationY(0f)
}

