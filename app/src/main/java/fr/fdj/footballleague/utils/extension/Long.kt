package fr.fdj.footballleague.utils.extension

/**
 * Returns the Long value which is expressed by the digits in the String. If the String is null,
 * empty or contains non digits characters, the return value will be null.
 * If the value exceeds the maximum value for a Long, the return value will be [Long.MAX_VALUE].
 *
 * @return the Long value which is expressed by the digits of the String
 */
// Using a custom method focusing on digits only instead of the native method allows to improve
// performances
fun String?.asSimpleUnsignedLongOrNull(): Long? {
    if(this.isNullOrEmpty()) {
        return null
    }
    var result = 0L
    for(c: Char in this) {
        if(result > Long.MAX_VALUE / 10L) {
            return Long.MAX_VALUE
        }
        if(c in '0'..'9') {
            val currentDigit = (c - '0').toLong()
            if(result > Long.MAX_VALUE - currentDigit) {
                return Long.MAX_VALUE
            }
            result = (result * 10L) + currentDigit
        } else {
            return null
        }
    }
    return result
}