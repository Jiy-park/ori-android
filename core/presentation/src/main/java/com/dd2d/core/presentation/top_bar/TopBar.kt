package com.dd2d.core.presentation.top_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.button.BackButton
import com.dd2d.core.presentation.main_text.Main600Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTitleTopBar(
    title: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.White,
    ),
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Main600Text(
                text = title,
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
            )
        },
        navigationIcon = {
            onBack?.let {
                BackButton(onClick = onBack)
            }
        },
        actions = actions,
        colors = colors,
        modifier = modifier
    )
}