package com.mc.toysimplemonkey.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mc.toysimplemonkey.R
import com.mc.toysimplemonkey.ui.theme.AppTheme

@Composable
fun CpDialog(
    title: String,
    onDismiss: () -> Unit,
    loading: Boolean = false,
    footer: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .widthIn(max = 560.dp)
                .fillMaxHeight(0.9f)
                .padding(8.dp),
            color = AppTheme.colors.background,
            shadowElevation = 8.dp,
        ) {
            Box {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Header
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(start = 20.dp, end = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            IconButton(
                                modifier = Modifier.size(40.dp),
                                onClick = onDismiss,
                            ) {
                                CpIcon(
                                    modifier = Modifier.size(24.dp),
                                    id = R.drawable.ic_close,
                                )
                            }
                        }
                        HorizontalDivider(color = AppTheme.colors.line)
                    }

                    // Body
                    Box(modifier = Modifier.weight(1f), content = content)

                    // Footer
                    if (footer != null) {
                        Column {
                            HorizontalDivider(color = AppTheme.colors.line)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                content = footer,
                            )
                        }
                    }
                }

                if (loading) {
                    CpLoadingOverlay()
                }
            }
        }
    }
}