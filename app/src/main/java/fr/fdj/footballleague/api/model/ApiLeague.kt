package fr.fdj.footballleague.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import fr.fdj.footballleague.model.League
import fr.fdj.footballleague.model.Sport
import fr.fdj.footballleague.utils.extension.asSimpleUnsignedLongOrNull

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

/**
 * Returns a new instance of [League] with the same data as in the [ApiLeague] instance.
 *
 * @return a new instance of [League] with the same data as in the [ApiLeague] instance
 */
fun ApiLeague.toLeague() : League = League(
    id.asSimpleUnsignedLongOrNull()?:0,
    name,
    Sport.fromName(sport)?:Sport.UNKNOWN,
    alternateName
)