package fr.fdj.footballleague.ui.business.main

import fr.fdj.footballleague.model.League

/**
 * Intents to be handled for the main screen
 */
sealed class MainIntent {
    /**
     * Intent for fetching the list of leagues.
     */
    data object FetchLeagues: MainIntent()
}