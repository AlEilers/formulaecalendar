package de.ae.formulaecalendar.app.view.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import de.ae.formulaecalendar.app.view.main.calendar.CalendarFragment
import de.ae.formulaecalendar.app.view.main.driverstandings.DriverStandingsFragment
import de.ae.formulaecalendar.app.view.main.teamstandings.TeamStandingsFragment

/**
 * Created by aeilers on 17.02.2017.
 */
class PageAdapter(fm: FragmentManager?, val numOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> CalendarFragment()
        1 -> DriverStandingsFragment()
        2 -> TeamStandingsFragment()
        else -> null
    }

    override fun getCount() = numOfTabs
}