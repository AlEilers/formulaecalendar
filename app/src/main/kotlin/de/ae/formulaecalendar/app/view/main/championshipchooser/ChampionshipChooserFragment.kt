package de.ae.formulaecalendar.app.view.main.championshipchooser

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Toast
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampionshipsData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by alexa on 05.09.2017.
 */
class ChampionshipChooserFragment() : DialogFragment() {
    val model: DataStore = RemoteStore
    val observer: Scheduler = AndroidSchedulers.mainThread()
    val subscriber: Scheduler = Schedulers.newThread()

    var adapter: ArrayAdapter<String>? = null
    var championships: ChampionshipsData? = null
    var notify: ((String) -> Unit)? = null

    fun init(context: Context, notify: (value: String) -> Unit): DialogFragment {
        this.notify = notify

        adapter = ArrayAdapter(context, android.R.layout.select_dialog_item)

        model.getAllChampionShips()
                .subscribeOn(subscriber)
                .observeOn(observer)
                .subscribe(object : Observer<ChampionshipsData?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {
                        adapter?.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable?) {
                        Toast.makeText(context, R.string.connection_fault, Toast.LENGTH_LONG).show()
                    }

                    override fun onNext(data: ChampionshipsData?) {
                        championships = data
                        data?.champsData
                                ?.map { it.championship }
                                ?.forEach { adapter?.add(it) }
                    }

                })

        return this
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.chooser_select_season)
                .setNegativeButton(R.string.chooser_negative, DialogInterface.OnClickListener { dialog, id ->

                })
                .setAdapter(adapter, { dialog, value ->
                    val championshipId = championships?.champsData
                            ?.map { it.championshipId }
                            ?.get(value) ?: ""
                    notify?.invoke(championshipId)
                })
        // Create the AlertDialog object and return it
        return builder.create()
    }
}