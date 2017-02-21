package de.ae.formulaecalendar.view.main;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.ae.formulaecalendar.remote.DataStore;
import de.ae.formulaecalendar.remote.pojo.series.ChampsDatum;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 13.01.2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class MainPresenterTest {
    private static final String CHAMPIONSHIP_NAME = "TEST_CC";
    private static final String CHAMPIONSHIP_NAME_2 = "Test_cc";
    private String viewTitle = null;

    @Mock
    DataStore model;

    private MainView view = new MainView() {
        @Override
        public void setTitle(String title) {
            viewTitle = title;
        }
    };

    @Before
    public void initialize() {
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void loadContent() {
        viewTitle = null;

        ChampsDatum datum = new ChampsDatum();
        datum.setChampionship(CHAMPIONSHIP_NAME);
        when(model.getCurrentChampionShip()).thenReturn(Observable.just(datum));

        MainPresenter p = new MainPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadContent();
        Assert.assertEquals(viewTitle, CHAMPIONSHIP_NAME_2);
    }

    @Test
    public void loadContentError() {
        viewTitle = CHAMPIONSHIP_NAME;

        when(model.getCurrentChampionShip()).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        MainPresenter p = new MainPresenter(view, model, Schedulers.immediate(), Schedulers.immediate());
        p.loadContent();
        Assert.assertEquals(viewTitle, CHAMPIONSHIP_NAME);
    }
}
