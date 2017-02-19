package de.ae.formulaecalendar.resource

/**
 * Created by aeilers on 18.02.2017.
 */
interface ResourceStore {

    fun getResources(): List<Resource>

    fun getResourceId(name: String): Int?
}