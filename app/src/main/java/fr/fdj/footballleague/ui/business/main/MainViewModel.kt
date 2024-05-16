package fr.fdj.footballleague.ui.business.main

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.footballleague.model.League
import fr.fdj.footballleague.model.Team
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

    private var allLeagues: List<League> = listOf()
    private var teams: List<Team> = listOf()
    private var teamsError = false
    private var textValue: TextFieldValue = TextFieldValue("")
    private var selectedLeague: League? = null

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
                }
            }
        }
    }

    /**
     * Fetches the list of leagues and displays it in the log
     */
    private fun fetchAllLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                allLeagues = leagueRepository.getAllLeagues()
                updateState()
            } catch (t: Throwable) {
                updateErrorState()
            }
        }
    }

    private fun fetchTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            selectedLeague?.let {
                try {
                    teams = leagueRepository.searchAllTeams(it)
                    teamsError = false
                } catch(t: Throwable) {
                    teamsError = true
                }
            }
            updateState()
        }
    }

    private fun onTextValueChanged(value: TextFieldValue) {
        textValue = value
        selectedLeague = null
        teams = listOf()
        teamsError = false
        updateState()
    }

    private fun onMenuItemSelected(league: League) {
        textValue = TextFieldValue(league.name)
        selectedLeague = league
        updateState()
        fetchTeams()
    }

    private fun updateState() {
        _state.value = MainState.LeaguesRetrievedState(
            textValue = textValue,
            onValueChanges = ::onTextValueChanged,
            menuValues = allLeagues.filter {
                val returnValue = it.name.lowercase().contains(textValue.text.lowercase()) || (it.alternateName?.lowercase()?.contains(textValue.text.lowercase())?:false)
                returnValue
            },
            onMenuItemSelected = ::onMenuItemSelected,
            teams = teams,
            teamsError = teamsError,
            retryTeams = ::fetchTeams
        )
    }

    private fun updateErrorState() {
        _state.value = MainState.ErrorState(
            retryLeagues = ::fetchAllLeagues
        )
    }
}