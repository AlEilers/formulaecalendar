package de.ae.formulaecalendar.view.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import de.ae.formulaecalendar.view.main.calendar.CalendarFragment
import de.ae.formulaecalendar.view.main.driverstandings.DriverStandingsFragment
import de.ae.formulaecalendar.view.main.teamstandings.TeamStandingsFragment

/**
 * Created by alexa on 17.02.2017.
 */
class PageAdapter(fm: FragmentManager?, val numOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return CalendarFragment()
            1 -> return DriverStandingsFragment()
            2 -> return TeamStandingsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }
}