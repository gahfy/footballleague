package fr.fdj.footballleague.repository

import fr.fdj.footballleague.model.League

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
}