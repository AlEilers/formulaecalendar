package de.ae.formulaecalendar.view.details;

import android.content.Context;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.ae.formulaecalendar.R;
import de.ae.formulaecalendar.formulaerest.DataStore;
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum;
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData;
import de.ae.formulaecalendar.formulaerest.pojo.race.SesRace;
import de.ae.formulaecalendar.formulaerest.pojo.race.Session;
import de.ae.formulaecalendar.resource.LocalResourceStore;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 13.01.2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class DetailsPresenterTest {
    private static final String RESOURCE_NO_RACE = "no upcoming race";
    private static final String TEST_CITY = "Berlin";
    private static final String TEST_RACE_NAME = TEST_CITY + " ePrix";
    private static final String TEST_ROUND = "1";
    private static final String RESOURCE_ROUND = "Round";
    private static final String ROUND_RESULT = RESOURCE_ROUND + " " + TEST_ROUND;
    private static final String TEST_RACEID = "0815";
    private static final Date TEST_DATE = new Date(1920754800000l); //2030/11/13
    private static final String RESOURCE_DATE = "LLL dd, yyyy";
    private static final String RESOURCE_TIME = "hh:mm a";
    private static final String TEST_DATE_RESULT = "Nov 13, 2030";
    private static final String TEST_QUALI = "12:00 PM - 01:00 PM";
    private static final String TEST_RACE = "04:00 PM - 05:00 PM";
    private static final String TEST_DISTANCE = "80000";
    private static final String RESOURCE_DISTANCE = "km";
    private static final String TEST_DISTANCE_RESULT = "80 " + RESOURCE_DISTANCE;
    private static final String TEST_LAPS = "50";
    private static final String RESOURCE_LAPS = "laps";
    private static final String TEST_LAPS_RESULT = TEST_LAPS + " " + RESOURCE_LAPS;
    private static final String RESOURCE_MAP = "%s in Google Maps";
    private static final String TEST_MAP = TEST_CITY + " in Google Maps";
    private static final String TEST_DRIVER_NAME = "TEST";

    private int viewResourceId = -1;
    private String viewTitle = null;
    private boolean viewRaceLoadingVisible = false;
    private boolean viewMainVisible = false;
    private boolean viewRaceSnackbarVisibility = false;
    private String viewRound = null;
    private String viewDate = null;
    private String viewQuali = null;
    private String viewRace = null;
    private String viewDistance = null;
    private String viewLaps = null;
    private String viewMap = null;
    private boolean viewNoResultsVisibility = false;
    private boolean viewLoadingResultsVisibility = false;
    private boolean viewResultsVisibility = false;
    private boolean viewResultsSnackbarVisibility = false;
    private List<SesRace> viewResults = null;

    private RaceCalendarData raceCalendarData;
    private CalendarDatum calendarDatum;

    @Mock
    Context context;

    @Mock
    DataStore model;

    @Before
    public void initialize() {
        PowerMockito.mockStatic(Log.class);

        //create race
        calendarDatum = new CalendarDatum();
        calendarDatum.setSequence(TEST_ROUND);
        calendarDatum.setCity(TEST_CITY);
        calendarDatum.setRaceName(TEST_RACE_NAME);
        calendarDatum.setLaps(TEST_LAPS);
        calendarDatum.setRaceDistance(TEST_DISTANCE);
        calendarDatum.setRaceDate(TEST_DATE);
        calendarDatum.setHasRaceResults(false);
        calendarDatum.setRaceId(TEST_RACEID);
        raceCalendarData = new RaceCalendarData();
        ArrayList<CalendarDatum> data = new ArrayList<>();
        data.add(calendarDatum);
        raceCalendarData.setCalendarData(data);

        //reset values
        viewResourceId = -1;
        viewTitle = null;
        viewRaceLoadingVisible = false;
        viewRaceSnackbarVisibility = false;
        viewMainVisible = false;
        viewRound = null;
        viewDate = null;
        viewQuali = null;
        viewRace = null;
        viewDistance = null;
        viewLaps = null;
        viewMap = null;
        viewNoResultsVisibility = false;
        viewLoadingResultsVisibility = false;
        viewResultsVisibility = false;
        viewResultsSnackbarVisibility = false;
        viewResults = null;
    }

    private DetailsView view = new DetailsView() {
        @Override
        public Context getContext() {
            when(context.getString(R.string.no_event)).thenReturn(RESOURCE_NO_RACE);
            when(context.getString(R.string.cal_event_round)).thenReturn(RESOURCE_ROUND);
            when(context.getString(R.string.format_date)).thenReturn(RESOURCE_DATE);
            when(context.getString(R.string.format_time)).thenReturn(RESOURCE_TIME);
            when(context.getString(R.string.details_laps)).thenReturn(RESOURCE_LAPS);
            when(context.getString(R.string.details_distance)).thenReturn(RESOURCE_DISTANCE);
            when(context.getString(R.string.details_on_map)).thenReturn(RESOURCE_MAP);
            return context;
        }

        @Override
        public void setImage(int resourceId) {
            viewResourceId = resourceId;
        }

        @Override
        public void setTitle(String title) {
            viewTitle = title;
        }

        @Override
        public void setRaceLoadingVisibility(boolean visible) {
            viewRaceLoadingVisible = visible;
        }

        @Override
        public void setMainViewVisibility(boolean visible) {
            viewMainVisible = visible;
        }

        @Override
        public void setRaceSnackbarVisibility(boolean visible) {
            viewRaceSnackbarVisibility = visible;
        }

        @Override
        public void setRound(String round) {
            viewRound = round;
        }

        @Override
        public void setDate(String date) {
            viewDate = date;
        }

        @Override
        public void setQualiTime(String time) {
            viewQuali = time;
        }

        @Override
        public void setRaceTime(String time) {
            viewRace = time;
        }

        @Override
        public void setDistance(String distance) {
            viewDistance = distance;
        }

        @Override
        public void setLaps(String laps) {
            viewLaps = laps;
        }

        @Override
        public void setMap(String map) {
            viewMap = map;
        }

        @Override
        public void setNoResultsVisibility(boolean visible) {
            viewNoResultsVisibility = visible;
        }

        @Override
        public void setResultsLoadingVisibility(boolean visible) {
            viewLoadingResultsVisibility = visible;
        }

        @Override
        public void setResultsVisibility(boolean visible) {
            viewResultsVisibility = visible;
        }

        @Override
        public void setResultsSnackbarVisibility(boolean visible) {
            viewResultsSnackbarVisibility = visible;
        }

        @Override
        public void setResults(List<SesRace> results) {
            viewResults = results;
        }
    };

    @Test
    public void loadContentNoRace() {

        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(raceCalendarData));

        DetailsPresenter presenter = new DetailsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline(), LocalResourceStore.INSTANCE);
        presenter.loadData(null);

        Assert.assertTrue(viewResourceId == R.drawable.berlin);
        Assert.assertEquals(TEST_RACE_NAME, viewTitle);
        Assert.assertFalse(viewRaceLoadingVisible);
        Assert.assertFalse(viewRaceSnackbarVisibility);
        Assert.assertTrue(viewMainVisible);
        Assert.assertEquals(ROUND_RESULT, viewRound);
        Assert.assertEquals(TEST_DATE_RESULT, viewDate);
        Assert.assertEquals(TEST_RACE, viewRace);
        Assert.assertEquals(TEST_QUALI, viewQuali);
        Assert.assertEquals(TEST_DISTANCE_RESULT, viewDistance);
        Assert.assertEquals(TEST_LAPS_RESULT, viewLaps);
        Assert.assertEquals(TEST_MAP, viewMap);
        Assert.assertTrue(viewNoResultsVisibility);
        Assert.assertFalse(viewLoadingResultsVisibility);
        Assert.assertFalse(viewResultsVisibility);
        Assert.assertFalse(viewResultsSnackbarVisibility);
    }

    @Test
    public void loadContentWithRace() {
        DetailsPresenter presenter = new DetailsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline(), LocalResourceStore.INSTANCE);
        presenter.loadData(calendarDatum);

        Assert.assertTrue(viewResourceId == R.drawable.berlin);
        Assert.assertEquals(TEST_RACE_NAME, viewTitle);
        Assert.assertFalse(viewRaceLoadingVisible);
        Assert.assertFalse(viewRaceSnackbarVisibility);
        Assert.assertTrue(viewMainVisible);
        Assert.assertEquals(ROUND_RESULT, viewRound);
        Assert.assertEquals(TEST_DATE_RESULT, viewDate);
        Assert.assertEquals(TEST_RACE, viewRace);
        Assert.assertEquals(TEST_QUALI, viewQuali);
        Assert.assertEquals(TEST_DISTANCE_RESULT, viewDistance);
        Assert.assertEquals(TEST_LAPS_RESULT, viewLaps);
        Assert.assertEquals(TEST_MAP, viewMap);
        Assert.assertTrue(viewNoResultsVisibility);
        Assert.assertFalse(viewLoadingResultsVisibility);
        Assert.assertFalse(viewResultsVisibility);
        Assert.assertFalse(viewResultsSnackbarVisibility);
    }

    @Test
    public void loadContentError() {
        when(model.getCurrentRaceCalendar()).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        DetailsPresenter presenter = new DetailsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline(), LocalResourceStore.INSTANCE);
        presenter.loadData(null);
        Assert.assertFalse(viewRaceLoadingVisible);
        Assert.assertFalse(viewMainVisible);
        Assert.assertTrue(viewRaceSnackbarVisibility);
        Assert.assertFalse(viewNoResultsVisibility);
        Assert.assertFalse(viewLoadingResultsVisibility);
        Assert.assertFalse(viewResultsVisibility);
        Assert.assertFalse(viewResultsSnackbarVisibility);
    }

    @Test
    public void loadResults() {

        SesRace sesRace = new SesRace();
        sesRace.setDriverName(TEST_DRIVER_NAME);
        ArrayList<SesRace> result = new ArrayList<>();
        result.add(sesRace);
        Session session = new Session();
        session.setSesRace(result);
        when(model.getRaceResult(TEST_RACEID)).thenReturn(Observable.just(session));

        DetailsPresenter presenter = new DetailsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline(), LocalResourceStore.INSTANCE);
        calendarDatum.setHasRaceResults(true);
        presenter.loadData(calendarDatum);
        Assert.assertEquals(TEST_DRIVER_NAME, viewResults.get(0).getDriverName());
        Assert.assertFalse(viewNoResultsVisibility);
        Assert.assertFalse(viewLoadingResultsVisibility);
        Assert.assertTrue(viewResultsVisibility);
        Assert.assertFalse(viewResultsSnackbarVisibility);
    }

    @Test
    public void loadResultsError() {
        when(model.getRaceResult(TEST_RACEID)).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        DetailsPresenter presenter = new DetailsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline(), LocalResourceStore.INSTANCE);
        calendarDatum.setHasRaceResults(true);
        presenter.loadData(calendarDatum);
        Assert.assertFalse(viewNoResultsVisibility);
        Assert.assertFalse(viewLoadingResultsVisibility);
        Assert.assertFalse(viewResultsVisibility);
        Assert.assertTrue(viewResultsSnackbarVisibility);
    }
}
