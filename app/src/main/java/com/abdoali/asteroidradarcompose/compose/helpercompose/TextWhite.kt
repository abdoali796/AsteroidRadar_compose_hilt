package com.abdoali.asteroidradarcompose.compose.helpercompose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextWhite(
    text:String,
    modifier: Modifier=Modifier
    ,color: Color=Color.White
) {
    Text(text = text, color = color,modifier=modifier)
}