package com.abdoali.asteroidradarcompose.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abdoali.asteroidradarcompose.R.drawable
import com.abdoali.asteroidradarcompose.R.string
import com.abdoali.asteroidradarcompose.compose.detailsSceen.navigToDetail
import com.abdoali.asteroidradarcompose.compose.helpercompose.AsterItem
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids

@Composable
fun AsteroidLazy(
    asteroid: List<Asteroids>, navController: NavController, modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.background(color = Color.Black)
    ) {
        items(asteroid) {
            AsteroidItemS(asteroid = it, clickAction = { navController.navigToDetail(it.id) })
        }


    }


}


@Composable
fun AsteroidItemS(
    asteroid: Asteroids,
    clickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (asteroid.isPotentiallyHazardous) Color.Red else Color.White
    val painter =
        if (!asteroid.isPotentiallyHazardous) painterResource(id = drawable.safe) else painterResource(
            id = drawable.non_safa
        )
    var expand by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.animateContentSize()
    ) {
        Card(
            backgroundColor = Color.Black,
            modifier = modifier.clickable {
                expand = !expand
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()

            ) {


                Column {
                    Text(
                        text = asteroid.codename,
                        fontSize = 20.sp,
                        modifier = modifier,
                        color = color
                    )
                    Text(
                        text = asteroid.closeApproachDate,
                        fontSize = 12.sp,
                        modifier = modifier,
                        color = color
                    )
                }
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = modifier
                        .size(60.dp)
                )

            }
        }
        if (expand) {

            AsterCard(asteroid = asteroid, clickAction)

        }
    }
}


@Composable
private fun AsterCard(asteroid: Asteroids, onClickAction: () -> Unit) {
    val color = if (asteroid.isPotentiallyHazardous) Color.Red else Color.White
    Column(
        Modifier
            .background(color)
            .padding(5.dp)
            .fillMaxWidth()

    ) {
        AsterItem(stringResource(id = string.close_approach_data_title), asteroid.closeApproachDate)
        AsterItem(
            stringResource(id = string.distance_from_earth_title),
            asteroid.distanceFromEarth.toString()
        )
        AsterItem(
            stringResource(id = string.estimated_diameter_title),
            asteroid.estimatedDiameter.toString()
        )
        Button(onClick = onClickAction, colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(text = stringResource(string.see_more), color = Color.White)
        }
    }

}


@Preview
@Composable
fun Item() {
    AsteroidItemS(asteroid = Asteroids(
        444444444444444,
        "ff",
        "442", 33.23, 23.3, 23.3, 23.3, true
    ), clickAction = { /*TODO*/ })
}