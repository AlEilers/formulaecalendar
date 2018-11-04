package de.ae.formulaecalendar.app.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.championshipchooser.ChampionshipChooserFragment
import de.ae.formulaecalendar.app.view.main.sharedvalues.SharedViewModel
import de.ae.formulaecalendar.app.view.settings.MyPreferenceActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity constructor() : AppCompatActivity(), MainView {
    private val presenter: MainPresenter = MainPresenter(this)

    private var sharedViewModel: SharedViewModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        presenter.loadContent()
        presenter.manageCalendar(this.applicationContext)
        presenter.scheduleNotifications(this.applicationContext)

        // get sharedViewModel
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        sharedViewModel?.getSelectedSeason()?.observe(this, Observer { presenter.loadContent(it) })


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
            presenter.openPlayStore(this)
            true
        }
        R.id.action_feedback -> {
            presenter.giveFeedback(this)
            true
        }
        R.id.action_filter -> {
            ChampionshipChooserFragment()
                    .register { sharedViewModel?.select(it) }
                    .show(supportFragmentManager, "championshipId")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}
