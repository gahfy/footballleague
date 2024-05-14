package fr.fdj.footballleague.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model which represents the list of leagues returned by the API.
 *
 * @property leagues the list of leagues which are returned by the API.
 *
 * @constructor Instantiates a new [ApiAllLeaguesResponse]
 *
 * @param leagues the list of leagues returned by the API to set
 *
 * @see ApiLeague
 */
@JsonClass(generateAdapter = true)
data class ApiAllLeaguesResponse(
    @Json(name = "leagues")
    val leagues: List<ApiLeague>
)