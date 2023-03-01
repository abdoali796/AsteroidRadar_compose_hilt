package com.abdoali.asteroidradarcompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = colorBlack,
    primaryVariant = Purple700,
    secondary = Teal200
)


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */


@Composable
fun AsteroidRadarcomposeTheme(

    content: @Composable () -> Unit
) {
//always dark themed
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}