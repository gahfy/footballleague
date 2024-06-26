package fr.fdj.footballleague.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LightColors = lightColorScheme(
    primary = FDJ_BLUE,
    background = WHITE,
    onBackground = BLACK
)

val DarkColors = darkColorScheme(
    primary = FDJ_BLUE,
    background = BLACK,
    onBackground = WHITE
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = getColorScheme(
        useDarkTheme = useDarkTheme,
        useDynamicColors = useDynamicColors
    )
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
        }
    }


    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = getColorScheme(
        useDarkTheme = useDarkTheme,
        useDynamicColors = useDynamicColors
    )
    Surface(
        modifier = modifier,
        color = colors.background,
        content = content
    )
}

@Composable
private fun getColorScheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean
) : ColorScheme {
    val context = LocalContext.current
    return when {
        (useDynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            if (useDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColors
        else -> LightColors
    }
}
