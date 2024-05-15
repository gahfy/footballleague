package fr.fdj.footballleague.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.footballleague.api.service.LeagueService
import fr.fdj.footballleague.repository.LeagueRepository
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
    private val leagueRepository: LeagueRepository
): ViewModel() {
    /**
     * Fetches the list of leagues and displays it in the log
     */
    fun fetchAllLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.v("FDJ", "${leagueRepository.getAllLeagues()}")
        }
    }
}