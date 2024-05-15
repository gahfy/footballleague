package fr.fdj.footballleague.repository

import fr.fdj.footballleague.api.model.toLeague
import fr.fdj.footballleague.api.service.LeagueService
import fr.fdj.footballleague.model.League

/**
 * League repository fetching data from the API.
 *
 * @property leagueService the API service to retrieve the list of leagues
 *
 * @constructor Instantiates a new [LeagueRepositoryImpl].
 *
 * @param leagueService the API service to retrieve the list of leagues to set
 */
class LeagueRepositoryImpl(
    private val leagueService: LeagueService
) : LeagueRepository {
    override suspend fun getAllLeagues(): List<League> {
        return leagueService.getAllLeagues().leagues.map {
            it.toLeague()
        }
    }
}