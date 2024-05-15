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

    /**
     * Intent for fetching the list of teams for a league.
     *
     * @property league the league for which to fetch the list of teams
     *
     * @constructor Instantiates a new [FetchTeams].
     *
     * @param league the list for which to fetch the list of teams to set
     */
    data class FetchTeams(val league: League): MainIntent()
}