package com.abdoali.asteroidradarcompose.compose.detailsSceen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val DETAIL_SCREEN="DETAIL_SCREEN"
private const val ID="IDddddddddddd"

fun NavGraphBuilder.detailScreen(){
    composable("$DETAIL_SCREEN/{$ID}",
    arguments = listOf(navArgument(ID){NavType.LongType})
        ){
        ConstraintAster()
    }
}

fun NavController.navigToDetail(id:Long){
    navigate("$DETAIL_SCREEN/$id")
}

fun getId(savedStateHandle: SavedStateHandle):Long?{
    val m: String? =savedStateHandle[ID]
    Log.i("abdoalllll", m.toString())
    return m?.toLong()
}