package com.abdoali.asteroidradarcompose.compose.picture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdoali.asteroidradarcompose.PictureOfDay
import com.abdoali.asteroidradarcompose.datalayer.repository.PicRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMPicture @Inject constructor(
    private val picRepos: PicRepos
) : ViewModel() {

    private var _pic = MutableStateFlow<PictureOfDay?>(null)
    val pic: StateFlow<PictureOfDay?>
        get() = _pic

    init {
        getPic()
    }

    private fun getPic() {
        viewModelScope.launch {
            _pic.value = picRepos.getPic()
        }
    }


}