package com.abdoali.asteroidradarcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.abdoali.asteroidradarcompose.compose.HomeScreen
import com.abdoali.asteroidradarcompose.compose.LongingScreen
import com.abdoali.asteroidradarcompose.ui.theme.AsteroidRadarcomposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsteroidRadarcomposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var showLandingScreen by remember { mutableStateOf(true) }
                    if (showLandingScreen){
                        LongingScreen (onTimeout = { showLandingScreen = false })
                    }else {

                        HomeScreen()
                    }
                }
            }
        }
    }
}

