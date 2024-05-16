package fr.fdj.footballleague.api.model

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import fr.fdj.footballleague.model.Team
import fr.fdj.footballleague.utils.extension.asSimpleUnsignedLongOrNull

/**
 * Model which represents a team as it is in the API.
 *
 * @property id       the unique identifier of the team
 * @property name     the name of the team
 * @property badgeUrl the url of the image of the badge of the team
 *
 * @constructor Instantiates a new [ApiTeam].
 *
 * @param id       the unique identifier of the team to set
 * @param name     the name of the team to set
 * @param badgeUrl the url of the image of the badge of the team to set
 */
@JsonClass(generateAdapter = true)
data class ApiTeam(
    @Json(name = "idTeam")
    val id: String,

    @Json(name = "strTeam")
    val name: String,

    @Json(name = "strTeamBadge")
    val badgeUrl: String
) {
    /**
     * Returns a [Team] with the same data as in the [ApiTeam] instance.
     *
     * @return a [Team] with the same data as in the [ApiTeam] instance.
     */
    fun toTeam(): Team {
        return Team(
            id = id.asSimpleUnsignedLongOrNull()?:0,
            name = name,
            badgeUrl = Uri.parse(badgeUrl)
        )
    }
}