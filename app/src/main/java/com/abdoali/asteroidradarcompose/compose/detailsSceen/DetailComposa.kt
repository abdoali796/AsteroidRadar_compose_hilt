package com.abdoali.asteroidradarcompose.compose.detailsSceen


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdoali.asteroidradarcompose.R
import com.abdoali.asteroidradarcompose.compose.helpercompose.AsterItem
import com.abdoali.asteroidradarcompose.compose.helpercompose.TextWhite

@Composable
fun ConstraintAster(

    vmDetail: VMDetail = hiltViewModel()
) {
    val shewAler = remember {
        mutableStateOf(false)
    }
    val asteroid by vmDetail.asteroid.collectAsState()

    val scrollableStat = rememberScrollState()
    val test = asteroid?.isPotentiallyHazardous
    ConstraintLayout(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(scrollableStat)
    ) {

        val (image, textClose, textAbsolut, codename, textDimer, textRest, textDistance, icon) = createRefs()
        val paint = if (test == true) {
            painterResource(id = R.drawable.asteroid_hazardous)
        } else {
            painterResource(
                id = R.drawable.asteroid_safe
            )
        }

        Image(painter = paint,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(image, constrainBlock = {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                )
                .fillMaxWidth()
        )

        asteroid?.let {
            AsterItem(info = stringResource(R.string.codo_name),
                info2 = it.codename,
                color = Color.White,
                modifier = Modifier.constrainAs(codename, constrainBlock =
                {
                    top.linkTo(image.bottom, 15.dp)
                    start.linkTo(parent.start, 10.dp)
                }
                ))
        }

        asteroid?.let {
            AsterItem(info = stringResource(id = R.string.close_approach_data_title),
                info2 = it.closeApproachDate,
                color = Color.White,
                modifier = Modifier.constrainAs(textClose, constrainBlock =
                {
                    top.linkTo(codename.bottom, 15.dp)
                    start.linkTo(parent.start, 10.dp)
                }
                ))
        }
        AsterItem(info = stringResource(id = R.string.absolute_magnitude_title),
            info2 = asteroid?.absoluteMagnitude.toString() + "  au",
            color = Color.White,
            modifier = Modifier.constrainAs(textAbsolut, constrainBlock =
            {
                top.linkTo(textClose.bottom, 15.dp)
                start.linkTo(parent.start, 10.dp)
            }
            ))
        AsterItem(info = stringResource(id = R.string.distance_from_earth_title),
            info2 = asteroid?.distanceFromEarth.toString() + "  au",
            color = Color.White,
            modifier = Modifier.constrainAs(textDistance, constrainBlock =
            {
                top.linkTo(textAbsolut.bottom, 15.dp)
                start.linkTo(parent.start, 10.dp)
            }
            ))
        AsterItem(info = stringResource(id = R.string.estimated_diameter_title),
            info2 = asteroid?.estimatedDiameter.toString() + "  km",
            color = Color.White,
            modifier = Modifier.constrainAs(textDimer, constrainBlock =
            {
                top.linkTo(textDistance.bottom, 15.dp)
                start.linkTo(parent.start, 10.dp)
            }
            ))
        AsterItem(info = stringResource(id = R.string.relative_velocity_title),
            info2 = asteroid?.relativeVelocity.toString() + "  Km/s",
            color = Color.White,
            modifier = Modifier.constrainAs(textRest, constrainBlock =
            {
                top.linkTo(textDimer.bottom, 15.dp)
                start.linkTo(parent.start, 10.dp)
            }
            ))

        Icon(imageVector = Icons.Default.Info, contentDescription = null, modifier = Modifier
            .clickable { shewAler.value = true }
            .constrainAs(icon, constrainBlock = {

                end.linkTo(parent.end, 10.dp)
                top.linkTo(textClose.bottom, 18.dp)

            })
        )
    }

    if (shewAler.value) {
        AlertDialog(onDismissRequest = { shewAler.value = false },
            title = {
                TextWhite(text = stringResource(id = R.string.help))
            },
            text = {
                TextWhite(text = stringResource(id = R.string.unit_explanation))

            },
            buttons = {
                Button(onClick = { shewAler.value = false }) {
                    TextWhite(text = "ok")
                }
            })
    }


}


@Preview(showBackground = true)
@Composable
fun Constrant() {
    ConstraintAster(

    )
}