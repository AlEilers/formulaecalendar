package de.ae.formulaecalendar.app.view.main.calendar;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.ae.formulaecalendar.formulaerest.DataStore;
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 12.01.2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class CalendarPresenterTest {
    private RaceCalendarData raceCalendarData = null;
    private boolean loadingViewVisible = false;
    private boolean recyclerViewVisible = false;
    private boolean snackbarVisible = false;

    @Mock
    DataStore model;

    CalendarView view = new CalendarView() {
        @Override
        public void setContent(RaceCalendarData data) {
            raceCalendarData = data;
        }

        @Override
        public void setLoadingViewVisibility(boolean visible) {
            loadingViewVisible = visible;
        }

        @Override
        public void setRecyclerViewVisibility(boolean visible) {
            recyclerViewVisible = visible;
        }

        @Override
        public void setSnackbarVisibility(boolean visible) {
            snackbarVisible = visible;
        }
    };

    @Before
    public void initialize() {
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void loadContent() {
        raceCalendarData = null;
        loadingViewVisible = true;
        recyclerViewVisible = false;
        snackbarVisible = true;


        RaceCalendarData data = new RaceCalendarData();
        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(data));

        CalendarPresenter p = new CalendarPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadContent();
        Assert.assertNotNull(raceCalendarData);
        Assert.assertFalse(loadingViewVisible);
        Assert.assertTrue(recyclerViewVisible);
        Assert.assertFalse(snackbarVisible);
    }

    @Test
    public void loadContentError() {
        raceCalendarData = null;
        loadingViewVisible = true;
        recyclerViewVisible = true;
        snackbarVisible = false;

        when(model.getCurrentRaceCalendar()).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        CalendarPresenter p = new CalendarPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadContent();
        Assert.assertFalse(loadingViewVisible);
        Assert.assertFalse(recyclerViewVisible);
        Assert.assertTrue(snackbarVisible);
    }
}
