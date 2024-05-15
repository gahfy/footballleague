package fr.fdj.footballleague.model

/**
 * Model of a league to be used in the application.
 *
 * @property id            the unique identifier of the league
 * @property name          the name of the league
 * @property sport         the sport to which the league applies
 * @property alternateName the alternative name for the league
 *
 * @constructor Instantiates a new [League]
 *
 * @param id            the unique identifier of the league to set
 * @param name          the name of the league to set
 * @param sport         the sport to which the league applies to set
 * @param alternateName the alternative name for the league to set
 */
data class League(
    val id: Long,
    val name: String,
    val sport: Sport,
    val alternateName: String?
) {
    override fun toString() = name
}