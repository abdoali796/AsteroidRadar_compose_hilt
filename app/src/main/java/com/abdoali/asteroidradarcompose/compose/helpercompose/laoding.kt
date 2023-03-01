package com.abdoali.asteroidradarcompose.compose.helpercompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingCircular() {
    Column(verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
        , modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            Modifier.padding(PaddingValues(20.dp))
           , color = Color.White
        )
    }

}