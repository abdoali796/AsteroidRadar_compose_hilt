package com.abdoali.asteroidradarcompose.compose.helpercompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AsterItem(
    info:String,
    info2: String
    ,modifier: Modifier = Modifier
    ,color: Color = Color.Black
) {
    Column(modifier =modifier.padding(horizontal = 5.dp)) {
        Text(text = info , fontSize = 13.sp, color = color)
        Text(text = info2 , fontSize = 10.sp, color = color)
    }
}


