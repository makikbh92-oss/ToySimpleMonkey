package com.mc.toysimplemonkey.ui.component.layout.responsive


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ReferenceHeight
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding

/**
 * 높이는 부모를 채우고, 너비는 자식 크기에 맞추는 박스. (SpaceType 없음)
 *
 * - 패딩의 base는 이 박스의 높이(부모 maxHeight)
 * - 자식에 fillMaxWidth / fillMaxSize를 주면 너비가 부모 폭까지 늘어나니 주지 말 것
 * - ResponsiveDp / ResponsivePadding / ReferenceHeight는 기존 파일의 것을 그대로 사용
 */
@Composable
fun CpFitBoxUnFixed(
    modifier: Modifier = Modifier,
    padding: ResponsivePadding = ResponsivePadding.Default,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxHeight(),
        contentAlignment = contentAlignment,
        ) {
        val base = if (constraints.hasBoundedHeight) maxHeight else ReferenceHeight

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(padding.resolve(base)),
            contentAlignment = contentAlignment,
            content = content,
        )
    }
}