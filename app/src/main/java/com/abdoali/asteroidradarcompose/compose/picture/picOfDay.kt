package com.abdoali.asteroidradarcompose.compose.picture

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.abdoali.asteroidradarcompose.R
import com.abdoali.asteroidradarcompose.compose.helpercompose.TextWhite

@Composable
fun PictureOfDay(
    modifier: Modifier = Modifier,
    vmPicture: VMPicture = hiltViewModel()

) {
    val pictureOfDay by vmPicture.pic.collectAsState()
    Column(
        //if device rotate
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxWidth()
    ) {
        if (pictureOfDay == null || pictureOfDay?.mediaType != "image") {
            CircularProgressIndicator(
                color = Color.White
            )
            TextWhite(text = stringResource(id = R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet))
        } else {
            AsyncImage(
                model = pictureOfDay!!.url,
                contentDescription = pictureOfDay?.title,
                contentScale = ContentScale.FillWidth
            )
            TextWhite(text = pictureOfDay!!.title)

        }
    }
}