package fr.fdj.footballleague.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.footballleague.api.service.LeagueService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

/**
 * ViewModel of the main screen
 *
 * @constructor Instantiates a new [MainViewModel]
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val leagueService: LeagueService
): ViewModel() {
    fun fetchAllLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.v("FDJ", "${leagueService.getAllLeagues()}")
        }
    }
}