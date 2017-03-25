package de.ae.formulaecalendar.app.view.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.threetenabp.AndroidThreeTen
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.app.view.settings.MyPreferenceActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity constructor() : AppCompatActivity(), MainView {
    var presenter: MainPresenter? = null

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
                pager.setCurrentItem(tab?.position ?: 0)
            }
        })

        //load Content
        presenter = MainPresenter(this)
        presenter?.loadContent()
        presenter?.manageCalendar(this)
        presenter?.scheduleNotifications(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_calendar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.action_settings -> {
                startActivity(Intent(this, MyPreferenceActivity::class.java))
                return true
            }
            R.id.action_rate -> {
                presenter?.openPlayStore(this)
                return true
            }
            R.id.action_feedback -> {
                presenter?.giveFeedback(this)
                return true
            }
            else ->
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item)
        }
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}
