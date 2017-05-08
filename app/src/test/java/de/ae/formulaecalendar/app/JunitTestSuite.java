package de.ae.formulaecalendar.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ae.formulaecalendar.app.view.about.AboutPresenterTest;
import de.ae.formulaecalendar.app.view.details.DetailsPresenterTest;
import de.ae.formulaecalendar.app.view.main.MainPresenterTest;
import de.ae.formulaecalendar.app.view.main.calendar.CalendarPresenterTest;
import de.ae.formulaecalendar.app.view.main.driverstandings.DriverStandingsPresenterTest;
import de.ae.formulaecalendar.app.view.main.teamstandings.TeamStandingsPresenterTest;
import de.ae.formulaecalendar.app.widget.CountdownWidgetPresenterTest;


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
        AboutPresenterTest.class,
        CountdownWidgetPresenterTest.class
})

public class JunitTestSuite {
}
