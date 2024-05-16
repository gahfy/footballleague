package fr.fdj.footballleague.api.service

import fr.fdj.footballleague.api.model.ApiAllLeaguesResponse
import fr.fdj.footballleague.api.model.ApiAllTeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service which retrieve results from the API.
 */
interface LeagueService {
    /**
     * Returns the list of all leagues from the API.
     *
     * @return the list of all leagues from the API
     */
    @GET("all_leagues.php")
    suspend fun getAllLeagues(): ApiAllLeaguesResponse

    /**
     * Returns the list of teams of the given league from the API.
     *
     * @param leagueName the name of the league for which to retrieve the teams
     *
     * @return the list of teams of the given league from the API
     */
    @GET("search_all_teams.php")
    suspend fun searchAllTeams(@Query("l") leagueName: String): ApiAllTeamsResponse
}