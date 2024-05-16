package fr.fdj.footballleague.repository

import fr.fdj.footballleague.api.service.LeagueService
import fr.fdj.footballleague.model.League
import fr.fdj.footballleague.model.Team

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

    override suspend fun searchAllTeams(league: League): List<Team> {
        return leagueService.searchAllTeams(league.name).teams.map {
            it.toTeam()
        }
            .sortedByDescending { it.name }
            .filterIndexed { index, team -> index%2 == 0 }
    }
}