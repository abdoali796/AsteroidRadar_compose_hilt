package com.abdoali.asteroidradarcompose.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.painterResource
import com.abdoali.asteroidradarcompose.R
import kotlinx.coroutines.delay


private const val SplashWaitTime: Long = 4000


@Composable
fun LongingScreen(
    onTimeout: () -> Unit
) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(true) {
        delay(SplashWaitTime)
        currentOnTimeout()
    }
    Image(painterResource(id = R.drawable.logoo), contentDescription = null)
}