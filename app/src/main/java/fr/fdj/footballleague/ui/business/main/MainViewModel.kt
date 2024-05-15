package fr.fdj.footballleague.ui.business.main

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.footballleague.model.League
import fr.fdj.footballleague.repository.LeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel of the main screen
 *
 * @property mainIntent the channel for the Intent to be consumed
 *
 * @constructor Instantiates a new [MainViewModel]
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository
): ViewModel() {
    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainState>(MainState.LoadingState)
    val state: StateFlow<MainState>
        get() = _state

    var allLeagues: List<League> = listOf()
    var textValue: TextFieldValue = TextFieldValue("")

    init {
        handleIntent()
    }

    /**
     * Handles the intent and perform the expected action when an intent is sent.
     */
    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchLeagues -> fetchAllLeagues()
                    is MainIntent.FetchTeams -> fetchTeams(it.league)
                }
            }
        }
    }

    /**
     * Fetches the list of leagues and displays it in the log
     */
    private fun fetchAllLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            allLeagues = leagueRepository.getAllLeagues()
            updateState()
        }
    }

    private fun fetchTeams(league: League) {
        // TODO: Develop this method
    }

    private fun onTextValueChanged(value: TextFieldValue) {
        textValue = value
        updateState()
    }

    private fun onMenuItemSelected(league: League) {
        textValue = TextFieldValue(league.name)
        updateState()
    }

    private fun updateState() {
        Log.e("Gahfy", "${textValue.text} / ${allLeagues}")
        _state.value = MainState.LeaguesRetrievedState(
            textValue = textValue,
            onValueChanges = ::onTextValueChanged,
            menuValues = allLeagues.filter {
                val returnValue = it.name.lowercase().contains(textValue.text.lowercase()) || (it.alternateName?.lowercase()?.contains(textValue.text.lowercase())?:false)
                Log.e("Gahfy", "$it => $returnValue")
                returnValue
            },
            onMenuItemSelected = ::onMenuItemSelected
        )
    }
}