package com.ziemowit.ts.ui_common.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.Blue40
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.Blue80
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.BlueGrey40
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.BlueGrey80
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.DarkBackground
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.DarkOnBackground
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.DarkOnSurface
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.DarkSurface
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.LightBackground
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.LightOnBackground
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.LightOnSurface
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.LightSurface
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.Red40
import com.ziemowit.ts.ui_common.theme.TSColor.Companion.Red80


private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = BlueGrey80,
    tertiary = Red80,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.LightGray,
    onSecondary = Color.LightGray,
    onTertiary = Color.LightGray,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface
)

// Updated Light Theme
private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = BlueGrey40,
    tertiary = Red40,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface
)

@Composable
fun TSTriviaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}