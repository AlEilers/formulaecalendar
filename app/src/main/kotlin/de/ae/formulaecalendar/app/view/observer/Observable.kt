package de.ae.formulaecalendar.app.view.observer

/**
 * Created by alexa on 05.09.2017.
 */
interface Observable<T> {
    val observer: MutableList<Observer<T>>

    fun notifyObservers(newValue: T) {
        observer.forEach { it.update(newValue) }
    }

    fun register(newObserver: Observer<T>) {
        observer.add(newObserver)
    }

    fun unregister(toRemove: Observer<T>) {
        observer.remove(toRemove)
    }

    fun getCurrentValue(): T

}