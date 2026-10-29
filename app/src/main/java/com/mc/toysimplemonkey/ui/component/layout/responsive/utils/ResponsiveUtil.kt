package com.mc.toysimplemonkey.ui.component.layout.responsive.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val ReferenceHeight = 891.dp

@Immutable
sealed interface ResponsiveDp {


    fun resolve(base: Dp): Dp


    @Immutable
    data class Fixed(val value: Dp) : ResponsiveDp {
        override fun resolve(base: Dp): Dp = value
    }


    @Immutable
    data class Ratio(
        val ratio: Float,
        val min: Dp = 0.dp,
        val max: Dp = Dp.Infinity,
    ) : ResponsiveDp {
        override fun resolve(base: Dp): Dp = (base * ratio).coerceIn(min, max)
    }

    companion object {
        fun scaled(
            dpAtReference: Dp,
            min: Dp = dpAtReference * 0.5f,
            max: Dp = dpAtReference * 1.5f,
        ): ResponsiveDp = Ratio(dpAtReference.value / ReferenceHeight.value, min, max)
    }
}

@Immutable
data class ResponsivePadding(
    val start: ResponsiveDp,
    val top: ResponsiveDp,
    val end: ResponsiveDp,
    val bottom: ResponsiveDp,
) {
    fun resolve(base: Dp) = PaddingValues(
        start = start.resolve(base),
        top = top.resolve(base),
        end = end.resolve(base),
        bottom = bottom.resolve(base),
    )

    companion object {
        val Default = all(ResponsiveDp.scaled(8.dp, min = 8.dp, max = 8.dp))

        val None = fixed(0.dp)
        val Button = symmetric(
            horizontal = ResponsiveDp.scaled(12.dp, min = 12.dp, max = 12.dp),
            vertical = ResponsiveDp.scaled(4.dp, min = 4.dp, max = 4.dp),
        )

        val Horizontal = symmetric(
            horizontal = ResponsiveDp.scaled(8.dp, min = 8.dp, max = 8.dp),
            vertical = ResponsiveDp.scaled(0.dp, min = 0.dp, max = 0.dp),
        )

        val Vertical = symmetric(
            horizontal = ResponsiveDp.scaled(0.dp, min = 0.dp, max = 0.dp),
            vertical = ResponsiveDp.scaled(8.dp, min = 8.dp, max = 8.dp),
        )

        val VerticalHalf = symmetric(
            horizontal = ResponsiveDp.scaled(8.dp, min = 8.dp, max = 8.dp),
            vertical = ResponsiveDp.scaled(4.dp, min = 4.dp, max = 4.dp),
        )



        fun all(value: ResponsiveDp) = ResponsivePadding(value, value, value, value)

        fun symmetric(horizontal: ResponsiveDp, vertical: ResponsiveDp) =
            ResponsivePadding(horizontal, vertical, horizontal, vertical)

        fun fixed(value: Dp) = all(ResponsiveDp.Fixed(value))
    }
}