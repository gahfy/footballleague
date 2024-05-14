package fr.fdj.footballleague.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model which represents a league as it is in the API.
 *
 * @property id            the unique identifier of the league
 * @property name          the name of the league
 * @property sport         the sport to which the league applies
 * @property alternateName an alternative name that can be used to identify the league
 *
 * @constructor Instantiates a new ApiLeague.
 *
 * @param id            the unique identifier of the league to set
 * @param name          the name of the league to set
 * @param sport         the sport of the league to set
 * @param alternateName the alternative name of the league to set
 */
@JsonClass(generateAdapter = true)
data class ApiLeague(
    @Json(name = "idLeague")
    val id: String,

    @Json(name = "strLeague")
    val name: String,

    @Json(name = "strSport")
    val sport: String,

    @Json(name = "strLeagueAlternate")
    val alternateName: String?
)