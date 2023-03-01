package com.abdoali.asteroidradarcompose.compose

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.abdoali.asteroidradarcompose.R
import com.abdoali.asteroidradarcompose.compose.picture.PictureOfDay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(

) {
    BackdropScaffold(appBar = { TapBar() },
        backLayerContent = { PictureOfDay() },
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerContent = { FrontLayer() })


}


@Composable
fun TapBar() {
    TopAppBar(backgroundColor = Color.Black) {
        Text(text = stringResource(R.string.app_name), color = Color.White)
    }
}

