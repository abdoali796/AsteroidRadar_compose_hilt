package com.abdoali.asteroidradarcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdoali.asteroidradarcompose.datalayer.repository.ReposImp
import com.abdoali.asteroidradarcompose.datalayer.repository.database.Asteroids
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repos: ReposImp
) : ViewModel() {
    private val TAG = "ViewModelTAG"


    private var _asterListToday = MutableStateFlow<List<Asteroids>?>(null)
    val asterListToday: StateFlow<List<Asteroids>?>
        get() = _asterListToday

    private var _asterList7day = MutableStateFlow<List<Asteroids>?>(null)
    val asterList7day: StateFlow<List<Asteroids>?>
        get() = _asterList7day

    private var _asterListSaved = MutableStateFlow<List<Asteroids>?>(null)
    val asterListSaved: StateFlow<List<Asteroids>?>
        get() = _asterListSaved

    private fun today() {
        viewModelScope.launch {
            repos.getTodayAster().collect {
                _asterListToday.value = it
                Log.i(TAG, "tO$it")
            }
        }
    }

    private fun seven() {
        viewModelScope.launch {
            repos.get7DayAster().collect {
                _asterList7day.value = it
                Log.i(TAG, "pso$it")

            }
        }
    }

    private fun saved() {
        viewModelScope.launch {
            repos.getSavedAster().collect {
                _asterListSaved.value = it
                Log.i(TAG, "sto$it")

            }
        }
    }


    init {
        updateDatabase()
        today()
        seven()
        saved()
    }

    private fun updateDatabase() {
        viewModelScope.launch {
            delay(3000)
            repos.updateDatabase()
        }
    }
}