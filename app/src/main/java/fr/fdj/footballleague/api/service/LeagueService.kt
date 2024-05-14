package fr.fdj.footballleague.api.service

import fr.fdj.footballleague.api.model.ApiAllLeaguesResponse
import retrofit2.http.GET

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
}