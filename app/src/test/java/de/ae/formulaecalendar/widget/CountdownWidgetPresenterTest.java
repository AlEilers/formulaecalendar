package de.ae.formulaecalendar.widget;

import android.content.Context;
import android.support.annotation.Nullable;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import de.ae.formulaecalendar.R;
import de.ae.formulaecalendar.remote.DataStore;
import de.ae.formulaecalendar.remote.pojo.calendar.CalendarDatum;
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 14.01.2017.
 */

@RunWith(MockitoJUnitRunner.class)
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
    private long viewNext = -1;

    private RaceCalendarData raceCalendarData;
    private CalendarDatum calendarDatum;

    @Mock
    DataStore model;

    @Mock
    Context context;

    @BeforeClass
    public static void setUp() {
        long diffTime = TEST_MILLIS + MILLIS_OF_16H - System.currentTimeMillis();
        int diffDays = (int) (diffTime / DAY_IN_MILLIS);
        int diffHours = (int) ((diffTime % DAY_IN_MILLIS) / HOUR_IN_MILLIS);
        RESULT_CURRENT_COUNTDOWN = diffDays + "d " + diffHours + 'h';
    }

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
        public void saveNext(long millis) {
            viewNext = millis;
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
        viewNext = -1;

        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadWidget(viewNext);

        Assert.assertEquals(TEST_RACE_NAME, viewTitle);
        Assert.assertEquals(RESULT_CURRENT_COUNTDOWN, viewCountdown);
        Assert.assertEquals(TEST_DATE_RESULT, viewDate);
        Assert.assertTrue(viewOpenDetails);
        Assert.assertEquals(TEST_MILLIS + MILLIS_OF_16H, viewNext); // Date + 16 hours
    }

    @Test
    public void loadWidgetNoDateError() {
        viewTitle = null;
        viewCountdown = null;
        viewDate = null;
        viewNext = -1;

        //TODO implement Error
        //when(model.getCurrentRaceCalendar()).thenReturn(Observable.error(new Exception("TEST", null)));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadWidget(viewNext);

        Assert.assertEquals("", viewTitle);
        Assert.assertEquals(RESOURCE_LOADING, viewCountdown);
        Assert.assertEquals("", viewDate);
        Assert.assertFalse(viewOpenDetails);
        Assert.assertEquals(-1, viewNext); // Date + 16 hours
    }

    @Test
    public void loadWidgetWithDate() {
        viewTitle = "TEST_CITY";
        viewCountdown = null;
        viewDate = "TEST_DATE";
        viewNext = TEST_MILLIS + MILLIS_OF_16H; // Date + 16 hours

        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadWidget(viewNext);

        Assert.assertEquals("TEST_CITY", viewTitle);
        Assert.assertEquals(RESULT_CURRENT_COUNTDOWN, viewCountdown);
        Assert.assertEquals("TEST_DATE", viewDate);
        Assert.assertTrue(viewOpenDetails);
        Assert.assertEquals(TEST_MILLIS + MILLIS_OF_16H, viewNext); // Date + 16 hours
    }

    @Test
    public void loadWidgetNoDateNull() {
        viewTitle = null;
        viewCountdown = null;
        viewDate = null;
        viewNext = -1;

        raceCalendarData.setCalendarData(new ArrayList<CalendarDatum>());
        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        CountdownWidgetPresenter p = new CountdownWidgetPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadWidget(viewNext);

        Assert.assertEquals("", viewTitle);
        Assert.assertEquals(RESOURCE_NO_RACE, viewCountdown);
        Assert.assertEquals("", viewDate);
        Assert.assertFalse(viewOpenDetails);
        Assert.assertEquals(-1, viewNext);
    }
}
