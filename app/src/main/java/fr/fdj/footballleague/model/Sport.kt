package fr.fdj.footballleague.model

/**
 * Class enumarating the list of sports to be used by the application.
 *
 * @property name the name of the sport
 *
 * @param name the name of the sport to set
 */
enum class Sport(
    name: String
) {
    /**
     * The Soccer sport
     */
    SOCCER("Soccer"),

    /**
     * To be used when the sport cannot be determined
     */
    UNKNOWN("Unknown");

    companion object{
        /**
         * Returns the [Sport] matching the given name, or null if no [Sport] matches that name.
         *
         * @param name the name of the [Sport] to return
         *
         * @return the [Sport] matching the given name, or null if no [Sport] matches that name.
         */
        fun fromName(name: String): Sport? {
            for(sport in Sport.entries) {
                if(sport.name.lowercase() == name.lowercase())
                    return sport
            }
            return null
        }
    }
}