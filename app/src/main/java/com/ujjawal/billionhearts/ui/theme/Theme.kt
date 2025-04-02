package com.ujjawal.billionhearts.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ujjawal.billionhearts.R

val gotham = FontFamily(
    Font(resId = R.font.gotham_bold, weight = FontWeight.Normal),
    Font(resId = R.font.gotham_medium, weight = FontWeight.Medium)
)
val avenir = FontFamily(
    Font(resId = R.font.avenir_black, weight = FontWeight.Normal),
    Font(resId = R.font.avenir_medium, weight = FontWeight.Medium),
    Font(resId = R.font.avenir_heavy, weight = FontWeight.SemiBold),
)
val avenir_roman = FontFamily(
    Font(resId = R.font.avenir_roman, weight = FontWeight.Normal),
)
val avenir_book = FontFamily(
    Font(resId = R.font.avenir_book, weight = FontWeight.Normal)
)

val avalon = FontFamily(
    Font(resId = R.font.avalon_regular, weight = FontWeight.Normal),
    Font(resId = R.font.avalon_bold, weight = FontWeight.Bold),
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BillionHeartsTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}