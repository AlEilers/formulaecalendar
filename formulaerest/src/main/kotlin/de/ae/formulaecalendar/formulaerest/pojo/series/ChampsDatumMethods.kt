package de.ae.formulaecalendar.formulaerest.pojo.series

/**
 * Created by alexa on 31.07.2017.
 */

/**
 * compare two ChampsDatum
 * 1. sort by status (Active -> Future -> Past)
 * 2. sort by championshipId (Date -> New to Old)
 */
fun ChampsDatum.compare(other: ChampsDatum): Int {
    val status = this.status?.compareTo(other.status ?: "") ?: 0

    if (status != 0) {
        return status
    } else {
        return other.championshipId?.compareTo(this.championshipId ?: "") ?: Int.MIN_VALUE
    }
}