package de.ae.formulaecalendar.app.view.observer

/**
 * Created by alexa on 05.09.2017.
 */
interface Observer<T> {
    fun update(newValue: T)
}