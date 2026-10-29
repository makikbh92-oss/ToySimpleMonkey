package com.mc.toysimplemonkey.ui.component.layout.scaffod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.mc.toysimplemonkey.ui.component.layout.responsive.CpLayout
import com.mc.toysimplemonkey.ui.theme.AppTheme

@Immutable
private data class ScreenRatio(val top: Float, val body: Float, val bottom: Float) {
    companion object {
        val Portrait = ScreenRatio(top = 7f, body = 84f, bottom = 9f)        // 일반 폰 세로
        val Landscape = ScreenRatio(top = 13f, body = 71f, bottom = 15f) // 폰 가로
    }
}


@Composable
fun CpScaffold(
    modifier: Modifier = Modifier,
    top: @Composable (BoxScope .() -> Unit)? = null,
    body: @Composable (BoxScope .() -> Unit),
    bottom: @Composable (BoxScope .() -> Unit)? = null,
) {
    Scaffold(
        modifier = modifier,
        containerColor = AppTheme.colors.background,
    ){
        innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CpLayout(){
                val isLandscape = maxWidth > maxHeight
                val ratio = if (isLandscape) ScreenRatio.Landscape else ScreenRatio.Portrait
                val bodyScale = ratio.body +
                        (if (top == null) ratio.top else 0f) +
                        (if (bottom == null) ratio.bottom else 0f)
                if (top != null) {
                    item(ratio.top) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            top()
                        }
                    }
                }
                item(bodyScale) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        body()
                    }
                }
                if(bottom != null) {
                    item(ratio.bottom) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            bottom()
                        }
                    }
                }
            }
        }

    }
}

