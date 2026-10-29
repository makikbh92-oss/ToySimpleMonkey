package com.mc.toysimplemonkey.core.packages.domain.vo.simple


enum class TelecomType(
    val index : Int,
    ) {
    SK(1),
    KT(2),
    LG(3)
}

//// presentation
//@DrawableRes
//fun TelecomType.logoRes(): Int = when (this) {
//    TelecomType.SK -> R.drawable.img_logo_skt
//    TelecomType.KT -> R.drawable.img_logo_kt
//    TelecomType.LG -> R.drawable.img_logo_lg
//}