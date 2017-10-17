package de.ae.formulaecalendar.app.view.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.threetenabp.AndroidThreeTen
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.championshipchooser.ChampionshipChooserFragment
import de.ae.formulaecalendar.app.view.observer.Observable
import de.ae.formulaecalendar.app.view.observer.Observer
import de.ae.formulaecalendar.app.view.settings.MyPreferenceActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.properties.Delegates


class MainActivity constructor() : AppCompatActivity(), MainView, Observable<String?> {
    var presenter: MainPresenter? = null

    override val observer: MutableList<Observer<String?>> = mutableListOf()
    var season: String by Delegates.observable("") { property, oldValue, newValue ->
        notifyObservers(newValue)
        presenter?.loadContent(newValue)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize ThreeTen
        AndroidThreeTen.init(this)

        //set Toolbar
        setSupportActionBar(toolbar_view)

        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_calendar))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_driver))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_team))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        pager.adapter = PageAdapter(supportFragmentManager, tab_layout.tabCount)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //do nothing
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //do nothing
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab?.position ?: 0
            }
        })

        //load Content
        presenter = MainPresenter(this)
        presenter?.loadContent()
        presenter?.manageCalendar(this.applicationContext)
        presenter?.scheduleNotifications(this.applicationContext)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_calendar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_settings -> {
            startActivity(Intent(this, MyPreferenceActivity::class.java))
            true
        }
        R.id.action_rate -> {
            presenter?.openPlayStore(this)
            true
        }
        R.id.action_feedback -> {
            presenter?.giveFeedback(this)
            true
        }
        R.id.action_filter -> {
            ChampionshipChooserFragment()
                    .register { season = it }
                    .show(supportFragmentManager, "championshipId")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun getCurrentValue(): String? {
        return season
    }
}
