package com.example.sonora.core.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val SonoraColorScheme = lightColorScheme(
    primary = BerryPrimary,
    background = SoftPink,
    surface = SoftCream,
    onPrimary = Color.White,
    onBackground = TextBerry,
    onSurface = TextBerry
)

@Composable
fun SonoraTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = SoftPink.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = SonoraColorScheme,
        typography = SonoraTypography,
        content = content
    )
}