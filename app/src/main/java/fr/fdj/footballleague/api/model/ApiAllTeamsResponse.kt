package fr.fdj.footballleague.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model which represents the list of teams returned by the API.
 *
 * @property teams the list of teams retrieved by the API
 *
 * @constructor Instantiates a new [ApiAllTeamsResponse].
 *
 * @param teams the list of the teams retrieved by the API to set
 */
@JsonClass(generateAdapter = true)
data class ApiAllTeamsResponse(
    @Json(name = "teams")
    val teams: List<ApiTeam>
)