package com.mc.toysimplemonkey.ui.component.layout.responsive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.FitType
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ReferenceHeight
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.ResponsivePadding
import com.mc.toysimplemonkey.ui.component.layout.responsive.util.SpaceType


@Composable
fun CpFitBoxFixed(
    modifier: Modifier = Modifier,
    space: SpaceType = SpaceType.Square,
    fit: FitType = FitType.Fit,
    padding: ResponsivePadding = ResponsivePadding.Default,
    spaceAlignment: Alignment = Alignment.Center,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit,
) {
    val ratio = space.ratio

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = spaceAlignment,
    ) {
        val boundedW = constraints.hasBoundedWidth
        val boundedH = constraints.hasBoundedHeight

        val width: Dp
        val height: Dp

        when (fit) {
            FitType.Height -> {
                height = if (boundedH) maxHeight else ReferenceHeight
                width = height * ratio
            }

            FitType.Width -> {
                width = if (boundedW) maxWidth else ReferenceHeight * ratio
                height = width / ratio
            }

            FitType.Fit -> {
                width = when {
                    boundedW && boundedH -> minOf(maxWidth, maxHeight * ratio)
                    boundedW -> maxWidth
                    boundedH -> maxHeight * ratio
                    else -> ReferenceHeight * ratio
                }
                height = width / ratio
            }
        }

        val base = if (boundedH) maxHeight else ReferenceHeight

        Box(
            modifier = Modifier
                .size(width = width, height = height)
                .padding(padding.resolve(base)),
            contentAlignment = contentAlignment,
            content = content,
        )
    }
}

// ═════════════════════════════════════════════
// Preview
// ═════════════════════════════════════════════

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun SquarePortraitPreview() {
    CpFitBoxFixed(space = SpaceType.Square) {
        Box(Modifier.background(Color.LightGray)) { Text("정사각형") }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,orientation=landscape")
@Composable
private fun WideLandscapePreview() {
    CpFitBoxFixed(
        space = SpaceType.Rectangle.Wide,
        padding = ResponsivePadding.fixed(12.dp),
    ) {
        Text("직사각형 16:9")
    }
}