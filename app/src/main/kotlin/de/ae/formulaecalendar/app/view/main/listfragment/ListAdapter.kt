package de.ae.formulaecalendar.app.view.main.listfragment

import android.support.v7.widget.RecyclerView

/**
 * Created by alexa on 12.11.2017.
 */
abstract class ListAdapter<S, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    abstract fun setContent(content: S)
}