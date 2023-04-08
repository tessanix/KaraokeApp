package com.mizikarocco.karaokeapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun ExtendsTheme(
    dimensions: Dimensions,
    orientation: Orientation,
    content: @Composable () ->Unit
) {
    val dimState = remember{dimensions}
    val orientationState = remember{orientation}

    CompositionLocalProvider(
        LocalAppDimens provides dimState,
        LocalOrientationMode provides orientationState,
        content = content
    )
}

val LocalAppDimens = compositionLocalOf {
    smallDimensions // Default parameter
}

val LocalOrientationMode = compositionLocalOf {
    Orientation.Portrait // Default parameter
}