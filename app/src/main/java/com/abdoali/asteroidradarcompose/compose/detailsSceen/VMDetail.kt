package com.abdoali.asteroidradarcompose.compose.detailsSceen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdoali.asteroidradarcompose.datalayer.repository.DetailRepos
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMDetail @Inject constructor(
    savedStateHandle: SavedStateHandle
,private val detailRepo:DetailRepos
):ViewModel() {
private val _asteroid= MutableStateFlow<Asteroids?>(null)
    val asteroid:StateFlow<Asteroids?>
    get() = _asteroid

init {
    getId(savedStateHandle)?.let { getAsteroid(it) }
}


    fun getAsteroid(id:Long){
        viewModelScope.launch {
        detailRepo.getAster(id).collect{
            _asteroid.value=it
        }
    }}
}