package de.ae.formulaecalendar.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.remote.RemoteStore
import de.ae.formulaecalendar.remote.pojo.series.ChampsDatum
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val model = RemoteStore
    val observer = AndroidSchedulers.mainThread()
    val subscriber = Schedulers.newThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getCurrentChampionShip()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Subscriber<ChampsDatum>() {
                    override fun onCompleted() {
                        println("complete")
                    }

                    override fun onError(e: Throwable?) {
                        println("error: $e")
                    }

                    override fun onNext(t: ChampsDatum?) {
                        println("fertig $t")
                    }

                })
    }
}
