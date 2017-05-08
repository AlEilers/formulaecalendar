package de.ae.formulaecalendar.app.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Date;

import de.ae.formulaecalendar.app.R;
import de.ae.formulaecalendar.formulaerest.DataStore;
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum;
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 14.01.2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class CountdownWidgetPresenterTest {
    private static final long HOUR_IN_MILLIS = (1000 * 60 * 60);
    private static final long DAY_IN_MILLIS = (1000 * 60 * 60 * 24);
    private static final long MILLIS_OF_16H = (16 * 60 * 60 * 1000);
    private static String RESULT_CURRENT_COUNTDOWN;

    private static final String RESOURCE_NO_RACE = "no upcoming reace";
    private static final String RESOURCE_LOADING = "Loading";
    private static final String TEST_CITY = "Berlin";
    private static final String TEST_RACE_NAME = TEST_CITY + " ePrix";
    private static final long TEST_MILLIS = 1920754800000l; //2030/11/13
    private static final Date TEST_DATE = new Date(TEST_MILLIS);
    private static final String RESOURCE_DATE = "LLL dd, yyyy";
    private static final String RESOURCE_TIME = "hh:mm a";
    private static final String TEST_DATE_RESULT = "Nov 13, 2030 04:00 PM";


    private String viewTitle = null;
    private String viewCountdown = null;
    private String viewDate = null;
    private boolean viewOpenDetails = false;
    private String viewNextTitle = "";
    private long viewNextMilli = -1;
    private String viewNextDate = "";

    private RaceCalendarData raceCalendarData;
    private CalendarDatum calendarDatum;

    @Mock
    DataStore model;

    @Mock
    Context context;

    CountdownWidgetView view = new CountdownWidgetView() {

        @Override
        public void setContent(@Nullable String title, @Nullable String countdown, @Nullable String date, boolean openDetails) {
            if (title != null) {
                viewTitle = title;
            }
            if (countdown != null) {
                viewCountdown = countdown;
            }
            if (date != null) {
                viewDate = date;
            }
            viewOpenDetails = openDetails;
        }

        @Override
        public void saveNext(String nextRace, long millis, String date) {
            viewNextTitle = nextRace;
            viewNextMilli = millis;
            viewNextDate = date;
        }

        @Override
        public Context getContext() {
            when(context.getString(R.string.widget_loading)).thenReturn(RESOURCE_LOADING);
            when(context.getString(R.string.widget_no_next)).thenReturn(RESOURCE_NO_RACE);
            when(context.getString(R.string.format_date)).thenReturn(RESOURCE_DATE);
            when(context.getString(R.string.format_time)).thenReturn(RESOURCE_TIME);
            return context;
        }
    };

    @Before
    public void initialize() {
        PowerMockito.mockStatic(Log.class);

        long diffTime = TEST_MILLIS + MILLIS_OF_16H - System.currentTimeMillis();
        int diffDays = (int) (diffTime / DAY_IN_MILLIS);
        int diffHours = (int) ((diffTime % DAY_IN_MILLIS) / HOUR_IN_MILLIS);
        RESULT_CURRENT_COUNTDOWN = diffDays + "d " + diffHours + 'h';

        //create race
        calendarDatum = new CalendarDatum();
        calendarDatum.setCity(TEST_CITY);
        calendarDatum.setRaceName(TEST_RACE_NAME);
        calendarDatum.setRaceDate(TEST_DATE);
        raceCalendarData = new RaceCalendarData();
        ArrayList<CalendarDatum> data = new ArrayList<>();
        data.add(calendarDatum);
        raceCalendarData.setCalendarData(data);
    }

    @Test
    public void loadWidgetNoDate() {
        viewTitle = null;
        viewCountdown = null;
        viewDate = null;
        viewNextTitle = "";
        viewNextMilli = -1;
        viewNextDate = "";

        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadWidget(viewNextTitle, viewNextMilli, viewNextDate);

        Assert.assertEquals(TEST_RACE_NAME, viewTitle);
        Assert.assertEquals(RESULT_CURRENT_COUNTDOWN, viewCountdown);
        Assert.assertEquals(TEST_DATE_RESULT, viewDate);
        Assert.assertTrue(viewOpenDetails);
        Assert.assertEquals(TEST_MILLIS + MILLIS_OF_16H, viewNextMilli); // Date + 16 hours
    }

    @Test
    public void loadWidgetNoDateError() {
        viewTitle = null;
        viewCountdown = null;
        viewDate = null;
        viewNextTitle = "";
        viewNextMilli = -1;
        viewNextDate = "";

        when(model.getCurrentRaceCalendar()).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadWidget(viewNextTitle, viewNextMilli, viewNextDate);

        Assert.assertEquals("", viewTitle);
        Assert.assertEquals(RESOURCE_LOADING, viewCountdown);
        Assert.assertEquals("", viewDate);
        Assert.assertFalse(viewOpenDetails);
        Assert.assertEquals("", viewNextTitle);
        Assert.assertEquals(-1, viewNextMilli); // Date + 16 hours
        Assert.assertEquals("", viewNextDate);
    }

    @Test
    public void loadWidgetWithDate() {
        viewTitle = "TEST_CITY";
        viewCountdown = null;
        viewDate = "TEST_DATE";
        viewNextTitle = "NEXT_TITLE";
        viewNextMilli = TEST_MILLIS + MILLIS_OF_16H; // Date + 16 hours
        viewNextDate = "NEXT_DATE";

        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadWidget(viewNextTitle, viewNextMilli, viewNextDate);

        Assert.assertEquals(viewNextTitle, viewTitle);
        Assert.assertEquals(RESULT_CURRENT_COUNTDOWN, viewCountdown);
        Assert.assertEquals(viewNextDate, viewDate);
        Assert.assertTrue(viewOpenDetails);
        Assert.assertEquals(viewNextTitle, viewNextTitle);
        Assert.assertEquals(TEST_MILLIS + MILLIS_OF_16H, viewNextMilli); // Date + 16 hours
        Assert.assertEquals(viewNextDate, viewNextDate);
    }

    @Test
    public void loadWidgetNoDateNull() {
        viewTitle = null;
        viewCountdown = null;
        viewDate = null;
        viewNextTitle = "";
        viewNextMilli = -1;
        viewNextDate = "";

        raceCalendarData.setCalendarData(new ArrayList<CalendarDatum>());
        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadWidget(viewNextTitle, viewNextMilli, viewNextDate);

        Assert.assertEquals("", viewTitle);
        Assert.assertEquals(RESOURCE_NO_RACE, viewCountdown);
        Assert.assertEquals("", viewDate);
        Assert.assertFalse(viewOpenDetails);
        Assert.assertEquals("", viewNextTitle);
        Assert.assertEquals(-1, viewNextMilli);
        Assert.assertEquals("", viewNextDate);
    }
}
