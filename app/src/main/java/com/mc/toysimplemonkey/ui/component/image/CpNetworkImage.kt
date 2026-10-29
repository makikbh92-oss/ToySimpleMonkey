package com.mc.toysimplemonkey.ui.component.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.ui.component.button.CpOutlineButton
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.theme.AppTheme
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun CpNetworkImage(
    url: String?,
    modifier: Modifier = Modifier,   // 기본: 부모 영역 전체
    defaultText: String = "이미지를 불러오지 못했습니다.",
    onClick: (() -> Unit)? = null,
    contentDescription: String? = null
) {
    var bitmap by remember(url) { mutableStateOf<Bitmap?>(null) }
    var failed by remember(url) { mutableStateOf(url.isNullOrBlank()) }

    val target = remember(url) {
        object : Target {
            override fun onBitmapLoaded(b: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap = b
                failed = false
            }
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                failed = true
            }
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }
    }

    DisposableEffect(url) {
        if (!url.isNullOrBlank()) {
            Picasso.get().load(url).into(target)
        }
        onDispose { Picasso.get().cancelRequest(target) }
    }

    val currentBitmap = bitmap

    Box(
        modifier = modifier.then(
            if (onClick != null && !failed)
                Modifier
                    .border(BorderStroke(1.dp, AppTheme.colors.background), RoundedCornerShape(4.dp))
                    .clickable(onClick = onClick)
            else Modifier
        ),
        contentAlignment = Alignment.Center
    ) {
        when {
            currentBitmap != null -> Image(
                bitmap = currentBitmap.asImageBitmap(),
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit,   // 원본 비율 유지하며 영역 안에 맞춤
                alignment = Alignment.Center,      // 중앙정렬
                modifier = Modifier.fillMaxSize()
            )

            failed && onClick != null -> CpOutlineButton(
                onClick = onClick,
                modifier = Modifier.fillMaxSize(),
                padding = ResponsivePadding.Default
            ) {
                Text(defaultText, style = AppTheme.typography.button)
            }

            failed -> Text(defaultText, style = AppTheme.typography.caption)

            else -> CircularProgressIndicator()
        }
    }
}