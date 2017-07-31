package de.ae.formulaecalendar.app.resource

/**
 * Created by aeilers on 18.02.2017.
 */
interface ResourceStore {

    fun getResources(): List<Resource>

    fun getResourceId(id: String): Int?
}