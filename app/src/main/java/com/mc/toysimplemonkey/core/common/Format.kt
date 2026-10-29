package com.mc.toysimplemonkey.core.common

import androidx.annotation.DrawableRes
import com.mc.toysimplemonkey.R
import com.mc.toysimplemonkey.core.packages.domain.vo.simple.TelecomType
import java.text.NumberFormat
import java.util.Locale


fun Int.toWon(): String = "${toComma()}원"


fun Int.toComma(): String =
    NumberFormat.getNumberInstance(Locale.KOREA).format(this)

@DrawableRes
fun TelecomType.toIconRes(): Int = when (this) {
    TelecomType.SK -> R.drawable.img_logo_skt
    TelecomType.KT -> R.drawable.img_logo_kt
    TelecomType.LG -> R.drawable.img_logo_lg
}