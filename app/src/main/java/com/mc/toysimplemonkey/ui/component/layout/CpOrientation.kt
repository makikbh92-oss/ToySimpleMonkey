package com.mc.toysimplemonkey.ui.component.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

enum class OrientationBasis {

    Screen,

    Area,
}


@Composable
fun CpOrientation(
    modifier: Modifier = Modifier,
    basedOn: OrientationBasis = OrientationBasis.Screen,
    landscape: @Composable () -> Unit,
    portrait: @Composable () -> Unit,
) {
    when (basedOn) {
        OrientationBasis.Screen -> {
            val isLandscape =
                LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
            Box(modifier = modifier.fillMaxSize()) {
                if (isLandscape) landscape() else portrait()
            }
        }

        OrientationBasis.Area -> {
            BoxWithConstraints(modifier = modifier.fillMaxSize()) {
                if (maxWidth >= maxHeight) landscape() else portrait()
            }
        }
    }
}

// ═════════════════════════════════════════════
// Preview
// ═════════════════════════════════════════════

@Preview(showBackground = true, name = "Area 가로", widthDp = 400, heightDp = 200)
@Composable
private fun AreaLandscapePreview() {
    CpOrientation(
        basedOn = OrientationBasis.Area,
        landscape = { Box(Modifier.fillMaxSize(), Alignment.Center) { Text("A (landscape)") } },
        portrait = { Box(Modifier.fillMaxSize(), Alignment.Center) { Text("B (portrait)") } },
    )
}

@Preview(showBackground = true, name = "Area 세로", widthDp = 200, heightDp = 400)
@Composable
private fun AreaPortraitPreview() {
    CpOrientation(
        basedOn = OrientationBasis.Area,
        landscape = { Box(Modifier.fillMaxSize(), Alignment.Center) { Text("A (landscape)") } },
        portrait = { Box(Modifier.fillMaxSize(), Alignment.Center) { Text("B (portrait)") } },
    )
}