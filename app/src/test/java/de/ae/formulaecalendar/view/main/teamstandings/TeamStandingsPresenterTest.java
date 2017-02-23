package de.ae.formulaecalendar.view.main.teamstandings;

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
import de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created by aeilers on 12.01.2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class TeamStandingsPresenterTest {
    ChampionshipData championshipData = null;
    private boolean loadingViewVisible = false;
    private boolean recyclerViewVisible = false;
    private boolean snackbarVisible = false;

    @Mock
    DataStore model;

    TeamStandingsView view = new TeamStandingsView() {

        @Override
        public void setContent(ChampionshipData data) {
            championshipData = data;
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
        championshipData = null;
        loadingViewVisible = true;
        recyclerViewVisible = false;
        snackbarVisible = true;

        ChampionshipData data = new ChampionshipData();
        when(model.getTeamStanding()).thenReturn(Observable.just(data));

        TeamStandingsPresenter p = new TeamStandingsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadContent();
        Assert.assertNotNull(championshipData);
        Assert.assertFalse(loadingViewVisible);
        Assert.assertTrue(recyclerViewVisible);
        Assert.assertFalse(snackbarVisible);
    }

    @Test
    public void loadContentError() {
        championshipData = null;
        loadingViewVisible = true;
        recyclerViewVisible = true;
        snackbarVisible = false;

        when(model.getTeamStanding()).thenReturn((Observable) Observable.error(new Exception("TEST", null)));

        TeamStandingsPresenter p = new TeamStandingsPresenter(view, model, Schedulers.trampoline(), Schedulers.trampoline());
        p.loadContent();
        Assert.assertFalse(loadingViewVisible);
        Assert.assertFalse(recyclerViewVisible);
        Assert.assertTrue(snackbarVisible);
    }
}
