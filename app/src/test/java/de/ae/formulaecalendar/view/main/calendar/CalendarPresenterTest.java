package de.ae.formulaecalendar.view.main.calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.ae.formulaecalendar.remote.DataStore;
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 12.01.2017.
 */

@RunWith(MockitoJUnitRunner.class)
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

    @Test
    public void loadContent() {
        raceCalendarData = null;
        loadingViewVisible = true;
        recyclerViewVisible = false;
        snackbarVisible = true;


        RaceCalendarData data = new RaceCalendarData();
        when(model.getCurrentRaceCalendar()).thenReturn(Observable.just(data));

        CalendarPresenter p = new CalendarPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
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

        //TODO implement Error
        //when(model.getCurrentRaceCalendar()).thenReturn(Observable.error(new Exception("TEST", null)));

        CalendarPresenter p = new CalendarPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadContent();
        Assert.assertFalse(loadingViewVisible);
        Assert.assertFalse(recyclerViewVisible);
        Assert.assertTrue(snackbarVisible);
    }
}
