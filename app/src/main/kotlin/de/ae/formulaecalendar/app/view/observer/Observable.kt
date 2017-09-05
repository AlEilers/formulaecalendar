package de.ae.formulaecalendar.app.view.observer

/**
 * Created by alexa on 05.09.2017.
 */
interface Observable {
    val observer: MutableList<Observer>

    fun notifyObservers(newValue: Any){
        observer.forEach{it.update(newValue)}
    }

    fun register(newObserver: Observer){
        observer.add(newObserver)
    }

    fun deregister(toRemove: Observer){
        observer.remove(toRemove)
    }

}