package de.ae.formulaecalendar;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ae.formulaecalendar.view.about.AboutPresenterTest;
import de.ae.formulaecalendar.view.details.DetailsPresenterTest;
import de.ae.formulaecalendar.view.main.MainPresenterTest;
import de.ae.formulaecalendar.view.main.calendar.CalendarPresenterTest;
import de.ae.formulaecalendar.view.main.driverstandings.DriverStandingsPresenterTest;
import de.ae.formulaecalendar.view.main.teamstandings.TeamStandingsPresenterTest;

/**
 * Created by aeilers on 12.01.2017.
 * <p>
 * If NoCalssDefFound: Context occurs run "gradlew assembleDebugUnitTest" in terminal
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DetailsPresenterTest.class,
        MainPresenterTest.class,
        CalendarPresenterTest.class,
        DriverStandingsPresenterTest.class,
        TeamStandingsPresenterTest.class,
        AboutPresenterTest.class
})

public class JunitTestSuite {
}
