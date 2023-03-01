package com.abdoali.asteroidradarcompose.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdoali.asteroidradarcompose.MainViewModel
import com.abdoali.asteroidradarcompose.R.string
import com.abdoali.asteroidradarcompose.compose.detailsSceen.detailScreen
import com.abdoali.asteroidradarcompose.compose.helpercompose.LoadingCircular
import com.abdoali.asteroidradarcompose.compose.helpercompose.TextWhite
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.launch


@Composable
fun FrontLayer(


) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListScreen) {
        composable(ListScreen) { AsteroidsList(navController) }
        detailScreen()
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun AsteroidsList(

    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {


    val asteriod by viewModel.asterListToday.collectAsState()
    val asteroid7 by viewModel.asterList7day.collectAsState()
    val asteroidSaved by viewModel.asterListSaved.collectAsState()
    val coroutine = rememberCoroutineScope()
    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val tile = listOf(
        stringResource(id = string.today_asteroids),
        stringResource(id = string.next_week_asteroids),
        stringResource(id = string.saved_asteroids)
    )



    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.White,
            backgroundColor = Color.Black
        ) {
            tile.forEachIndexed { index, tit ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        coroutine.launch {
                            pagerState.animateScrollToPage(index)

                        }
                    },
                    text = { TextWhite(text = tit) })
            }
        }
        HorizontalPager(
            tile.size,
            state = pagerState
        ) {

            when (it) {
                0 -> {
                    AsteroidList(asteroid = asteriod, navController)
                }
                1 -> {
                    AsteroidList(asteroid = asteroid7, navController)
                }

                2 -> {
                    AsteroidList(asteroid = asteroidSaved, navController)
                }
            }

        }
    }
}

@Composable
fun AsteroidList(
    asteroid: List<Asteroids>?, navController: NavController
) {

    if (asteroid == null) {
        LoadingCircular()
        return
    } else {
        AsteroidLazy(asteroid = asteroid, navController)
    }

}

const val ListScreen = "frontLayerScreen"
