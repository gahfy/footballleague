package fr.fdj.footballleague.repository

import fr.fdj.footballleague.model.League
import fr.fdj.footballleague.model.Team

/**
 * Repository for accessing data related to leagues
 */
interface LeagueRepository {
    /**
     * Returns the list of leagues from datasource.
     *
     * @return the list of leagues from datasource
     */
    suspend fun getAllLeagues(): List<League>

    /**
     * Returns the list of teams for the given league from datasource.
     *
     * @param league the league for which to get the list of teams
     *
     * @return the list of teams for the given league from datasource
     */
    suspend fun searchAllTeams(league: League): List<Team>
}