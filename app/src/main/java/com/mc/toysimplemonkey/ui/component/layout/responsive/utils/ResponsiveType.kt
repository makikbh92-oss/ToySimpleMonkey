package com.mc.toysimplemonkey.ui.component.layout.responsive.util

import androidx.compose.runtime.Immutable

@Immutable
sealed interface SpaceType {
    val ratio: Float

    /** 정사각형 (1 : 1) */
    data object Square : SpaceType {
        override val ratio: Float = 1f
    }

    /** 직사각형. 기본은 가로로 긴 4 : 3 */
    @Immutable
    data class Rectangle(override val ratio: Float = 4f / 3f) :
        SpaceType {
        init {
            require(ratio > 0f) { "ratio must be > 0" }
        }

        companion object {
            val Wide = Rectangle(16f / 9f)     // 가로로 긴 16:9
            val Tall = Rectangle(9f / 16f)     // 세로로 긴 9:16
        }
    }
}

/** 공간 크기를 무엇 기준으로 계산할지 */
enum class FitType {
    /** 부모 안에 비율을 유지하며 가장 크게 (width = min(maxWidth, maxHeight × ratio)) */
    Fit,

    /** 높이 기준: width = maxHeight × ratio, height = maxHeight */
    Height,

    /** 너비 기준: width = maxWidth, height = maxWidth / ratio */
    Width,
}