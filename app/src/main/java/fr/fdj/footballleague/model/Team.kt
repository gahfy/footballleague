package fr.fdj.footballleague.model

import android.net.Uri

/**
 * Model for a team to be used in the application.
 *
 * @property id       the unique identifier of the team
 * @property name     the name of the team
 * @property badgeUrl the url of the image of the badge of the team
 *
 * @constructor Instantiates a new [Team].
 *
 * @param id       the unique identifier of the team to set
 * @param name     the name of the team to set
 * @param badgeUrl the url of the image of the badge of the team to set
 */
data class Team(
    val id: Long,
    val name: String,
    val badgeUrl: Uri
)